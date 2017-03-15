package com.mystore.action;

import static org.junit.Assert.*;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts2.ServletActionContext;
import org.bson.Document;
import org.junit.Test;

import com.demo.pojo.Book;
import com.demo.pojo.User;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mystore.service.UserService;
import com.opensymphony.xwork2.ActionSupport;

public class UserAction extends ActionSupport {
	private static final long serialVersionUID = 1L;  
	private UserService userService;
	private User user;
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
	public User getUser(){return user;}
	public void setUser(User user){this.user=user;}
	public UserService getUserService()
	{
		return userService;
	}
	public void setUserService(UserService userService)
	{
		this.userService=userService;
	}
	public void addUser() throws IOException
	{
		this.userService.addUser(this.user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("添加成功");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void queryUser() throws IOException
	{	
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		request.setCharacterEncoding("utf-8");
		String keyword=request.getParameter("keyword");
		List<User>result=this.userService.queryUser(keyword);
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void updateUser() throws IOException
	{
		this.userService.updateUser(this.user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("修改成功");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void removeUser() throws IOException
	{
		this.userService.removeUser(this.user);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("删除成功");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void addProfile() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String profile=request.getParameter("profile");
		String UId=request.getParameter("UId");
		this.userService.addProfile(UId,profile);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void updateProfile() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String profile=request.getParameter("profile");
		String UId=request.getParameter("UId");
		this.userService.updateProfile(UId,profile);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void removeProfile() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String UId=request.getParameter("UId");
		this.userService.removeProfile(UId);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print("");
		response.getWriter().flush();
		response.getWriter().close();
	}
	public void queryProfile() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String UId=request.getParameter("UId");
		String result=this.userService.queryProfile(UId);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(result);
		response.getWriter().flush();
		response.getWriter().close();
	}
	public String upload() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		request.setCharacterEncoding("utf-8");
		String newFileName=request.getParameter("ID");

        File uploadFile=new File(ServletActionContext.getServletContext().getRealPath("upload"));  
        if(!uploadFile.exists()){  
            uploadFile.mkdir();
        }  
        int index = fileFileName.lastIndexOf('.');  
        if (index != -1)  
            newFileName = newFileName + fileFileName.substring(index);   
        FileInputStream input=new FileInputStream(file);  
        FileOutputStream out=new FileOutputStream(uploadFile + "\\" +newFileName);  
        try{  
            byte[] b=new byte[1024];  
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
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase mongoDatabase = mongoClient.getDatabase("userProfile"); 
		MongoCollection<Document> collection = mongoDatabase.getCollection("head");
		BufferedImage img = ImageIO.read(file);
		ByteArrayOutputStream buf = new ByteArrayOutputStream((int) file.length());
		ImageIO.write(img, "jpg", buf);
		byte[] b=buf.toByteArray();
		newFileName=request.getParameter("ID");
		Document document = new Document("UId",newFileName)
		.append("img", b).append("path", "upload/"+newFileName+".jpg");
		collection.insertOne(document);
        return "USER";
	}

	@SuppressWarnings("rawtypes")
	public void download() throws IOException
	{
		HttpServletRequest request = ServletActionContext.getRequest();
		String UId=request.getParameter("UId");
		Mongo mongo=new Mongo("localhost:27017");
		DB db=mongo.getDB("userProfile");
		DBCollection collection=db.getCollection("head");
		BasicDBObject condition = new BasicDBObject("UId",UId );
		DBObject dataset = collection.findOne(condition);
		String path = dataset.get("path").toString();
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType("text/html;charset=UTF-8");
		System.out.print(path);
		response.getWriter().print(path);
		response.getWriter().flush();
		response.getWriter().close();
		/*
		ByteArrayOutputStream bos = new ByteArrayOutputStream(); 
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpServletResponse response = ServletActionContext.getResponse();
		MongoClient mongoClient = new MongoClient( "localhost" , 27017 );
		MongoDatabase mongoDatabase = mongoClient.getDatabase("userProfile"); 
		MongoCollection<Document> collection = mongoDatabase.getCollection("head");
		String UId=request.getParameter("UId");
		BasicDBObject dbObject = new BasicDBObject();
		dbObject.put("UId",UId);
		FindIterable iterate = collection.find(dbObject).limit(1);
		Document d=(Document)iterate.iterator().tryNext();
		ObjectOutputStream oos=new ObjectOutputStream(bos);
		oos.writeObject(d.get("img"));          
        oos.flush();           
        byte[] bytes = bos.toByteArray();
        System.out.print(bytes);
        oos.close();           
        bos.close();  
		ServletOutputStream out=response.getOutputStream();
		out.write(bytes);
		out.flush();
		out.close();
		/*
		BufferedImage img = ImageIO.read(file);
		ImageIO.write(img, "jpg", buf);
		
		byte[] b=buf.toByteArray();
		String newFileName=request.getParameter("ID");
		Document document = new Document("UId",newFileName)
		.append("img", b);
		collection.insertOne(document);
		*/
	}
	public void countByUser() throws IOException
	{
		HttpServletResponse response = ServletActionContext.getResponse();
		List<Book>result=this.userService.countByUser(this.user);
		JSONArray arr = JSONArray.fromObject(result);
		String returnStr = arr.toString();
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().print(returnStr);
		response.getWriter().flush();
		response.getWriter().close();
	}
}
