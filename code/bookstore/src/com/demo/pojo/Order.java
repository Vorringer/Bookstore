package com.demo.pojo;

import java.sql.Timestamp;

/**
 * Order entity. @author MyEclipse Persistence Tools
 */

public class Order implements java.io.Serializable {

	// Fields

	private Integer OId;
	private String orderId;
	private Integer UId;
	private Timestamp time;
	private String address;

	// Constructors

	/** default constructor */
	public Order() {
	}

	/** full constructor */
	public Order(String orderId, Integer UId, Timestamp time, String address) {
		this.orderId = orderId;
		this.UId = UId;
		this.time = time;
		this.address = address;
	}
	public Order(Double UId,String address) {
		this.orderId = "h";
		this.UId = (int)(UId+0.5);
		this.time = null;
		this.address = address;
	}


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

	public Integer getUId() {
		return this.UId;
	}

	public void setUId(Integer UId) {
		this.UId = UId;
	}

	public Timestamp getTime() {
		return this.time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}