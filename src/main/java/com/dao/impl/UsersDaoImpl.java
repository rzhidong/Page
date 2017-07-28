package com.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.dao.IUsersDao;
import com.util.DBUtil;
import com.util.PageBean;
import com.vo.Users;

public class UsersDaoImpl implements IUsersDao {

	private Connection conn;

	private PreparedStatement ps;

	private ResultSet rs;

	@Override
	public void getAll(PageBean<Users> pageBean) {
		// TODO Auto-generated method stub

		// 查询总纪录数：设置到pageBean对象中
		int totalCount = getTotalCount("");

		pageBean.setTotalCount(totalCount);

		/*
		 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！ 如果当前页为末页，再点下一页显示有问题！ 
		 * 解决： 1. 如果当前页 <= 0;
		 * 当前页设置当前页为1; 2. 如果当前页 > 最大页数； 当前页设置为最大页数
		 */

		// 判断
		if (pageBean.getCurrentPage() <= 0) {
			// 把当前页设置为1
			pageBean.setCurrentPage(1);
		} else if (pageBean.getCurrentPage() > pageBean.getTotalPage()) {
			// 把当前页设置为最大页数
			pageBean.setCurrentPage(pageBean.getTotalPage());
		}

		// 1、获取当前页，计算查询的起始行、返回的行数
		int currentPage = pageBean.getCurrentPage();

		// 查询的起始行
		int index = (currentPage - 1) * pageBean.getPageCount();
		// 查询返回的行数
		int count = pageBean.getPageCount();

		// 2、分页查询数据；把查询到的数据设置到pageBean对象中
		String sql = "select * from users limit ?,?";

		conn = DBUtil.getConnection();

		List<Users> pageData = new ArrayList<Users>();

		try {
			ps = conn.prepareStatement(sql);

			ps.setInt(1, (currentPage - 1) * count);
			ps.setInt(2, count);

			rs = ps.executeQuery();

			while (rs.next()) {
				Users users = new Users();
				users.setId(rs.getInt("id"));
				users.setUsername(rs.getString("username"));
				users.setEmail(rs.getString("email"));
				users.setGrade(rs.getInt("grade"));
				pageData.add(users);
			}

			rs.close();
			ps.close();
			DBUtil.closeConnecton(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pageBean.setPageData(pageData);

	}

	@Override
	public int getTotalCount(String condition) {
		// TODO Auto-generated method stub
		String sql;

		if ("".equals(condition)) {
			sql = "select count(*) from users ";
		} else {
			sql = "select count(*) from users WHERE  username LIKE '%"
					+ condition + "%' OR email LIKE '%" + condition + "%';";
		}

		Connection conn = DBUtil.getConnection();

		PreparedStatement ps;

		ResultSet rs;

		int count = 0;

		try {
			ps = conn.prepareStatement(sql);

			rs = ps.executeQuery();

			while (rs.next()) {
				count = rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public void getByCondition(PageBean<Users> pageBean, String condition) {
		// TODO Auto-generated method stub
		
		int totalCount = getTotalCount(condition);
		
		pageBean.setTotalCount(totalCount);
		
		/*
		 * 问题： jsp页面，如果当前页为首页，再点击上一页报错！ 如果当前页为末页，再点下一页显示有问题！ 
		 * 解决： 1. 如果当前页 <= 0;
		 * 当前页设置当前页为1; 2. 如果当前页 > 最大页数； 当前页设置为最大页数
		 */

		// 判断
		if (pageBean.getCurrentPage() <= 0) {
			// 把当前页设置为1
			pageBean.setCurrentPage(1);
		} else if (pageBean.getCurrentPage() > pageBean.getTotalPage()) {
			// 把当前页设置为最大页数
			pageBean.setCurrentPage(pageBean.getTotalPage());
		}

		// 1、获取当前页，计算查询的起始行、返回的行数
		int currentPage = pageBean.getCurrentPage();

		// 查询的起始行
		int index = (currentPage - 1) * pageBean.getPageCount();
		// 查询返回的行数
		int count = pageBean.getPageCount();

		String sql = "SELECT * FROM users WHERE username LIKE '%" + condition
				+ "%' OR email LIKE '%" + condition + "%' limit ?,?;";
		
		List<Users> pageData = new ArrayList<Users>();

		conn = DBUtil.getConnection();

		try {
			ps = conn.prepareStatement(sql);
			
			ps.setInt(1, (currentPage - 1) * count);
			ps.setInt(2, count);

			rs = ps.executeQuery();

			while (rs.next()) {
				Users users = new Users();
				users.setId(rs.getInt("id"));
				users.setUsername(rs.getString("username"));
				users.setEmail(rs.getString("email"));
				users.setGrade(rs.getInt("grade"));
				pageData.add(users);
			}

			rs.close();
			ps.close();
			DBUtil.closeConnecton(conn);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		pageBean.setPageData(pageData);

	}

}
