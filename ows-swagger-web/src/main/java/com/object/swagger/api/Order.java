package com.object.swagger.api;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value="订单对象",description="这是一个订单对象")
public class Order {

	@ApiModelProperty(value = "订单编号", required = false)
	private int orderId;
	@ApiModelProperty(value = "订单名称", required = false)
	private String orderName;
	@ApiModelProperty(value = "时间", required = false)
	private Date time;
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}
	public String getOrderName() {
		return orderName;
	}
	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
}
