package com.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import com.util.DBUtil;

public class DataInsert {
	
	public static void main(String[] args) {
		
		Connection connection = DBUtil.getConnection();
		
		String sql = "insert into users(id,username,email,grade,passwd)"
				+ " values(?,?,?,?,?)";
		
		String data = DataSataic.getStaticData();
		
		int length = data.length();
		
		try {
			PreparedStatement ps = connection.prepareStatement(sql);
			
			for (int i = 1; i <= 1000; i++) {
				String nameSub = data.substring(i,i+3);
				int id = 1000 + i;
				String name = nameSub + id;
				int grade = new Random().nextInt(10);
				String emailSub = data.substring(length - i -3, length-i);
				ps.setInt(1, id);
				ps.setString(2, name);
				if (grade % 2 == 0) {
					ps.setString(3, emailSub + (id + grade*10)  +"@126.com");
				}else {
					ps.setString(3, emailSub + (id - grade*10)  +"@126.com");
				}
				ps.setInt(4, grade);
				ps.setString(5, name + grade);
				
				ps.execute();
				
			}
			
			DBUtil.closeConnecton(connection);
			ps.close();
			System.out.println("Done");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
