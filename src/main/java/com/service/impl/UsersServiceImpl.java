package com.service.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.service.IUsersService;
import com.util.DBUtil;
import com.util.JdbcUtil;
import com.util.PageBean;
import com.vo.Users;

public class UsersServiceImpl implements IUsersService {

	@Override
	public void getAll(PageBean<Users> pageBean) {
		// TODO Auto-generated method stub

		int totalCount = getTotalCount();
		
		pageBean.setTotalCount(totalCount);
		
		if (pageBean.getCurrentPage() <= 0) {
			pageBean.setCurrentPage(1);
		}else if (pageBean.getCurrentPage() > pageBean.getTotalPage()) {
			pageBean.setCurrentPage(pageBean.getTotalPage());
		}
		
		int currentPage = pageBean.getCurrentPage();
		
		int index = (currentPage - 1) * pageBean.getPageCount();
		
		int count = pageBean.getPageCount();
		
		String sql = "select * from users limit ?,?";
		
		try {
			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());
			
			List<Users> pageData = runner.query(sql, 
					new BeanListHandler<Users>(Users.class),index,count);
			
			pageBean.setPageData(pageData);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@Override
	public int getTotalCount() {
		// TODO Auto-generated method stub
		String sql = "select count(*) from users";
		
		long count = 0;

		try {
			QueryRunner runner = new QueryRunner(JdbcUtil.getDataSource());

			count = runner.query(sql, new ScalarHandler<Long>());
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return Integer.parseInt(String.valueOf(count));
	}
}
