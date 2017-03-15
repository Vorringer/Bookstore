package com.mystore.action;

import java.io.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.demo.pojo.Book;
import com.demo.pojo.Order;
import com.mystore.service.BookService;
public class BookAction extends ActionSupport {
	private static final long serialVersionUID = 1L;  
	private BookService bookService;
	private Book book;
	private File file;  
    
    public File getFile() {  
        return file;  
    }  
  
    public void setFile(File file) {  
        this.file = file;  
    }  
  
    public String getFileFileName() {  
        return fileFileName;  
    }  
  
    public void setFileFileName(String fileFileName) {  
        this.fileFileName = fileFileName;  
    }  
  
    public String getFileContentType() {  
        return fileContentType;  
    }  
  
    public void setFileContentType(String fileContentType) {  
        this.fileContentType = fileContentType;  
    }  
  
    //文件名称  
    private String fileFileName;  
      
    //文件类型  
    private String fileContentType;  
    //注意：文件名称和文件类型的名称前缀必须相同，  
	public Book getBook(){return book;}
	public void setBook(Book book){this.book=book;}
	public BookService getBookService()
	{
		return bookService;
	}
	public void setBookService(BookService bookService)
	{
		this.bookService=bookService;
	}

	public void queryBook() throws IOException
	{	
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		String keyword=request.getParameter("keyword");
		List<Book>result=this.bookService.queryBook(keyword);
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void queryBookByID() throws IOException
	{	
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Book>result=this.bookService.queryBookByID(this.book);
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void addBook() throws IOException
	{
		this.bookService.addBook(this.book); 
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("添加成功");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void updateBook() throws IOException
	{
		this.bookService.updateBook(this.book);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("修改成功");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void removeBook() throws IOException
	{
		this.bookService.removeBook(this.book);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("下架成功");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void countByBook() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Order>result=this.bookService.countByBook(this.book);
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void countByCategory() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Book>result=this.bookService.countByCategory(this.book);
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
	public String upload() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String newFileName=request.getParameter("ID");
		//获取需要上传文件的文件路径  
        File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("uploadFile"));  
        //判断文件是否上传，如果上传的话将会创建该目录  
        if(!uploadFile.exists()){  
            uploadFile.mkdir(); //创建该目录  
        }  
        int index = fileFileName.lastIndexOf('.');  
        // 判断上传文件名是否有扩展名  
        if (index != -1)  
            newFileName = newFileName + fileFileName.substring(index);   
        //第一种文件上传的方法  
        //声明文件输入流，为输入流指定文件路径  
        FileInputStream input=new FileInputStream(file);  
        //获取输出流，获取文件的文件地址及名称  
        FileOutputStream out=new FileOutputStream(uploadFile + "\\" +newFileName);  
          
        try{  
            byte[] b=new byte[1024];//每次写入的大小  
            int i=0;  
            while((i=input.read(b))>0){  
                out.write(b,0,i);  
            }  
        }catch(Exception e){  
            e.printStackTrace();  
        }finally{  
            input.close();  
            out.close();  
        }  
        return "ADMIN";
	}
}
