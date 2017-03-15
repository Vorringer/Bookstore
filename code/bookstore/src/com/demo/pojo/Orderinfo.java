package com.demo.pojo;

/**
 * Orderinfo entity. @author MyEclipse Persistence Tools
 */

public class Orderinfo implements java.io.Serializable {

	// Fields

	private Integer OId;
	private String orderId;
	private Integer BId;
	private Integer amount;

	// Constructors

	/** default constructor */
	public Orderinfo() {
	}

	/** full constructor */
	public Orderinfo(String orderId, Integer BId, Integer amount) {
		this.orderId = orderId;
		this.BId = BId;
		this.amount = amount;
	}

	// Property accessors

	public Integer getOId() {
		return this.OId;
	}

	public void setOId(Integer OId) {
		this.OId = OId;
	}

	public String getOrderId() {
		return this.orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Integer getBId() {
		return this.BId;
	}

	public void setBId(Integer BId) {
		this.BId = BId;
	}

	public Integer getAmount() {
		return this.amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

}