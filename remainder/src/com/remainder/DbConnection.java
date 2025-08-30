package com.remainder;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbConnection {
	
	public static Connection connect(){
		String url="jdbc:mysql://localhost:3306/test";

        String user="root";
        String pass="rcpit";
        
        Connection con=null;
    	
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    		con = DriverManager.getConnection(url,user,pass);
    	}catch (Exception e) {
			// TODO: handle exception
    		System.out.println(e.getMessage());
		}
    	
    	return con;
	}

}
