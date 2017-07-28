package com.dao;

import com.util.PageBean;
import com.vo.Users;

public interface IUsersDao {
	
	public void getAll(PageBean<Users> pageBean);
	
	public int getTotalCount(String condition);
	
	public void getByCondition(PageBean<Users> pageBean, String condition);

}
