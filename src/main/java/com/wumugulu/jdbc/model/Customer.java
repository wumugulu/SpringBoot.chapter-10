package com.wumugulu.jdbc.model;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class Customer implements RowMapper<Customer>, Serializable {
	
	private static final long serialVersionUID = 1L;

	private Integer cid;
	private String customerName;
	private String customerLevel;
	private String customerSource;
	private String customerPhone;
	private String customerMobile;

	public Integer getCid() {
		return cid;
	}
	public void setCid(Integer cid) {
		this.cid = cid;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerLevel() {
		return customerLevel;
	}
	public void setCustomerLevel(String customerLevel) {
		this.customerLevel = customerLevel;
	}
	public String getCustomerSource() {
		return customerSource;
	}
	public void setCustomerSource(String customerSource) {
		this.customerSource = customerSource;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	public String getCustomerMobile() {
		return customerMobile;
	}
	public void setCustomerMobile(String customerMobile) {
		this.customerMobile = customerMobile;
	}
	
	@Override
	public String toString() {
		return "Customer [cid=" + cid + ", customerName=" + customerName + ", customerLevel=" + customerLevel
				+ ", customerSource=" + customerSource + ", customerPhone=" + customerPhone + ", customerMobile="
				+ customerMobile + "]";
	}
	
	@Override
	public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
		Customer customer = new Customer();
		customer.setCid(rs.getInt("cid"));
		customer.setCustomerName(rs.getString("custname"));
		customer.setCustomerLevel(rs.getString("custlevel"));
		customer.setCustomerSource(rs.getString("custsource"));
		customer.setCustomerPhone(rs.getString("custphone"));
		customer.setCustomerMobile(rs.getString("custmobile"));
		
		return customer;
	}

}
