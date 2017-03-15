package com.demo.pojo;

/**
 * Book entity. @author MyEclipse Persistence Tools
 */

public class Book implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer BId;
	private String name;
	private String author;
	private String category;
	private Double price;
	private Integer amount;

	// Constructors

	/** default constructor */
	public Book() {
	}

	/** full constructor */
	public Book(String name, String author, String category, Double price,
			Integer amount) {
		this.name = name;
		this.author = author;
		this.category = category;
		this.price = price;
		this.amount = amount;
	}
	
	public Book(Integer BId, String name, String author, String category, Double price,
			Integer amount) {
		this.BId=BId;
		this.name = name;
		this.author = author;
		this.category = category;
		this.price = price;
		this.amount = amount;
	}
	public Book(String category, Double price)
	{	
		this.BId=0;
		this.name = "h";
		this.author = "h";
		this.category = category;
		this.price = price;
		this.amount = 0;
	}

	// Property accessors

	public Integer getBId() {
		return this.BId;
	}

	public void setBId(Integer BId) {
		this.BId = BId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return this.author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return this.category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Double getPrice() {
		return this.price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}