package com.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBUtil {
	
	private static Properties properties;
	
	private static String jdbcDriver;
	
	private static String jdbcUrl;
	
	private static String username;
	
	private static String password;
	
	private static Connection conn;
	
	public static Connection getConnection(){
		
		properties = new Properties();
		
		InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("com/util/db.properties");
		
		try {
			properties.load(is);
			
			jdbcDriver = properties.getProperty("jdbcDriver");
			jdbcUrl = properties.getProperty("jdbcUrl");
			username = properties.getProperty("username");
			password = properties.getProperty("password");
			
			Class.forName(jdbcDriver);
			
			conn = null;
			
			conn = DriverManager.getConnection(jdbcUrl, username, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return conn;
	}
	
	public static void closeConnecton(Connection conn){
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
