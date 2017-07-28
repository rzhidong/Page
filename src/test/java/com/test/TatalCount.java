package com.test;

import com.dao.impl.UsersDaoImpl;
import com.service.impl.UsersServiceImpl;

public class TatalCount {
	
	public static void main(String[] args) {
		
		//System.err.println(new UsersDaoImpl().getTotalCount());
		
		//System.out.println(new UsersServiceImpl().getTotalCount());
		
		System.out.println(DataSataic.getStaticData().length());
	}

}
