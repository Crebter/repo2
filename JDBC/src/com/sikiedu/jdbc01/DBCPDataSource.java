package com.sikiedu.jdbc01;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBCPDataSource {
	private static final String connectionUrl = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "wudonglong";
	
	private static BasicDataSource ds;
	
	static {
		//��ʼ��dbcp����Դ
		ds = new BasicDataSource();
		ds.setDriverClassName("com.mql.jdbc.Driver");
		ds.setUrl(connectionUrl);
		ds.setUsername(username);
		ds.setPassword(password);
		
		ds.setInitialSize(5);//��ʼ�����ӵĸ����������ӳ�����ĸ���
		ds.setMaxTotal(20);//���ӵ����������ֹ�����ƴ�������֤��������
		ds.setMinIdle(3);//��С�Ŀ������ӣ��൱�ڱ�����
	}
	
		//�������
	public Connection getConnection() {
		try {
			return ds.getConnection();//ͨ��dbcp�õ������ӣ�����Ҫ�黹��ֱ��close�Ϳ���
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	//�ر�
	public static void close(ResultSet rs,Statement stmt,Connection con) {
		closeResultSet(rs);
		closeStatement(stmt);
		closeConnection(con);
	}
	
	public static void close(Statement stmt1,Statement stmt2,Connection con) {
		closeStatement(stmt1);
		closeStatement(stmt2);
		closeConnection(con);
	}
	
	private static void closeResultSet(ResultSet rs) {
		try {
			if(rs!=null)
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void closeStatement(Statement stmt) {
		try {
			if(stmt!=null)
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private static void closeConnection(Connection con) {
		try {
			if(con!=null)
			con.close();//����Ͽ������ݿ�����ӣ����ǰ����ӷŻص����ӳ�����
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	
}
