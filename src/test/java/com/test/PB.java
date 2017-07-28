package com.test;

import com.dao.IUsersDao;
import com.dao.impl.UsersDaoImpl;
import com.service.IUsersService;
import com.service.impl.UsersServiceImpl;
import com.util.PageBean;
import com.vo.Users;

public class PB {

	public static void main(String[] args) {

		IUsersDao usersDao = new UsersDaoImpl();

		PageBean<Users> pageBean1 = new PageBean<Users>();

		pageBean1.setCurrentPage(3);
		usersDao.getAll(pageBean1);

		for (Users users : pageBean1.getPageData()) {
			//System.out.println(users);
		}
		
		IUsersService usersService = new UsersServiceImpl();
		
		PageBean<Users> pageBean2 = new PageBean<Users>();

		pageBean2.setCurrentPage(3);
		usersDao.getAll(pageBean2);

		for (Users users : pageBean2.getPageData()) {
			//System.out.println(users);
		}
		
		PageBean<Users> pageBean3 = new PageBean<Users>();
		
		pageBean3.setCurrentPage(1);
		usersDao.getByCondition(pageBean3, "之");
		
		for (Users users : pageBean1.getPageData()) {
			System.out.println(users);
		}

	}

}
