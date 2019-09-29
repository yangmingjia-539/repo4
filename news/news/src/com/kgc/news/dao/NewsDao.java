package com.kgc.news.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

public class NewsDao {
	// 查询新闻信息
	public void getNewsList(){
		Connection connection=null;
		Statement stmt=null;
		ResultSet rs=null;
		try {
			//（1）Class.forName()加载驱动
			Class.forName("com.mysql.jdbc.Driver");
			//（2）DriverManager.getConnection(URL,用户名,密码)获得数据库连接 (Connection)
			connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/news","root","root");
			//（3）获得Statement对象，执行SQL语句
			String sql="select * from news_detail";
			stmt=connection.createStatement();
			rs=stmt.executeQuery(sql);
			//（4）处理执行结果(ResultSet)，
			while(rs.next()){
				int id=rs.getInt("id");
				String title=rs.getString("title");
                String summary = rs.getString("summary");
				String content=rs.getString("content");
				String author=rs.getString("author");
				Timestamp time=rs.getTimestamp("createdate");
				System.out.println(id + "\t" + title + "\t" + summary + "\t"+ content + "\t" + author + "\t" + time);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			//释放资源
			try {
				rs.close();
				stmt.close();
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	//测试
	public static void main(String[] args) {
		NewsDao newsDao=new NewsDao();
		newsDao.getNewsList();
	}
}
