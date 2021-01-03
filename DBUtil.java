package cn.itcast.Store.util;

import java.sql.*;

public class DBUtil {

	static String user="root";
	static String password="123456";
	static String url="jdbc:mysql://localhost:3306/store?serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=utf-8";
//static String url="jdbc:mysql://localhost:3306/java?\tuseUnicode=true&characterEncoding=utf-8";

	static{
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		Connection conn=null;
		try {
			 conn=DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
	
	public static void closeJDBC(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
