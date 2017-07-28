package com.service;

import com.util.PageBean;
import com.vo.Users;

public interface IUsersService {
	
	public void getAll(PageBean<Users> pageBean);
	
	public int getTotalCount();

}
