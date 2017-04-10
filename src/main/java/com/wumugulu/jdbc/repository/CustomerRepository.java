package com.wumugulu.jdbc.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.wumugulu.jdbc.model.Customer;

@Repository
public class CustomerRepository {
	
	@Resource
	private JdbcTemplate jdbcTemplate;

	// 直接使用map接收select查询多条记录的返回数据
	public List<Map<String, Object>> getAll(Integer offset, Integer rows){
		String sql = "select cid, custname, custlevel, custsource, custphone, custmobile "
					+ " from t_customer limit ?, ?";
		List<Object> args = new ArrayList<>();
		args.add(offset);
		args.add(rows);
		
		Object[] temp = args.toArray();
		List<Map<String, Object>> resultMapList = jdbcTemplate.queryForList(sql, temp);

		return resultMapList;
	}

	// 使用Customer对象接收select查询多条记录的返回数据，需要Customer对象实现RowMapper<Customer>接口
	public List<Customer> findAll(Integer offset, Integer rows){
		String sql = "select cid, custname, custlevel, custsource, custphone, custmobile "
					+ " from t_customer limit ?, ?";
		List<Object> args = new ArrayList<>();
		args.add(offset);
		args.add(rows);
		
		List<Customer> list = jdbcTemplate.query(sql, args.toArray(), new Customer());

		return list;
	}

	// 查询单个数据结果（不是单条记录）
	public Integer getCount(){
		String sql = "select count(1) from t_customer";
		Integer result = jdbcTemplate.queryForObject(sql, Integer.class);
		
		return result;
	}

	// 查询单条记录，也使用了RowMapper
	public Customer find(Integer id){
		String sql = "select cid, custname, custlevel, custsource, custphone, custmobile "
					+ "from t_customer where cid = ?";
		List<Object> args = new ArrayList<>();
		args.add(id);
		
		Customer customer = null;
		// 使用这种方法，当返回记录为0条或者多条时都会出现异常，需要自行处理
		try {
			customer = jdbcTemplate.queryForObject(sql, args.toArray(), new Customer());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 使用下面这种方式麻烦点儿，但是可以避免异常的发生
//		List<Customer> list = jdbcTemplate.query(sql, args.toArray(), new Customer());
//		if (list.size()>0) {
//			customer = list.get(0);
//		}
		
		return customer;
	}

	// 新增表记录并使用KeyHolder获取自增id
	public Integer create(Customer customer){
		String sql = "insert into t_customer(custname, custlevel, custsource, custphone, custmobile) values(?,?,?,?,?)";
		KeyHolder keyHolder =new GeneratedKeyHolder();
		Integer result = jdbcTemplate.update(new PreparedStatementCreator() {
								@Override
								public PreparedStatement createPreparedStatement(Connection arg0) throws SQLException {
									PreparedStatement ps = jdbcTemplate.getDataSource()
																		.getConnection()
																		.prepareStatement(sql, new int[]{1,2,3,4,5});
									// ps.setInt(1, customer.getCid());
									ps.setString(1, customer.getCustomerName());
									ps.setString(2, customer.getCustomerLevel());
									ps.setString(3, customer.getCustomerSource());
									ps.setString(4, customer.getCustomerSource());
									ps.setString(5, customer.getCustomerMobile());
									
									return ps;
								}
							}, keyHolder);

		System.out.println("insert result = " + result);
		customer.setCid(keyHolder.getKey().intValue());
		return result;
	}

	// 新增表记录并使用select LAST_INSERT_ID()获取自增id
	public Integer create2(Customer customer){
		String sql = "insert into t_customer(custname, custlevel, custsource, custphone, custmobile)"
					+ " values(?,?,?,?,?)";
		List<Object> args = new ArrayList<>();
		// args.add(customer.getCid());
		args.add(customer.getCustomerName());
		args.add(customer.getCustomerLevel());
		args.add(customer.getCustomerSource());
		args.add(customer.getCustomerSource());
		args.add(customer.getCustomerMobile());
		
		Integer result = jdbcTemplate.update(sql, args.toArray());
		System.out.println("insert result = " + result);
		
		String sqlId = "SELECT LAST_INSERT_ID()";
		Integer id = jdbcTemplate.queryForObject(sqlId, Integer.class);
		customer.setCid(id);

		return result;
	}

	// 修改表记录
	public Integer update(Integer id, Customer customer){
		String sql = "update t_customer set custname=?, custlevel=?, custsource=?, custphone=?, custmobile=?"
				+ " where cid=?";
		List<Object> args = new ArrayList<>();
		args.add(customer.getCustomerName());
		args.add(customer.getCustomerLevel());
		args.add(customer.getCustomerSource());
		args.add(customer.getCustomerSource());
		args.add(customer.getCustomerMobile());
		args.add(id);
		Integer result = jdbcTemplate.update(sql, args.toArray());
		System.out.println("update result = " + result);

		return result;
	}

	// 删除表记录
	public Integer delete(Integer id){
		String sql = "delete from t_customer where cid=?";
		List<Object> args = new ArrayList<>();
		args.add(id);
		Integer result = jdbcTemplate.update(sql, args.toArray());
		System.out.println("delete result = " + result);

		return result;
	}
}
