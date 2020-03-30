package com.sikiedu.jdbc01;
//������һ��������ķ�����Ϊ��̬����������ͨ���������÷���
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
//�������ӵĴ���
public class JDBCUtils {
	//���ó������ں����޸��û���������
	private static final String connectionUrl = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
	private static final String username = "root";
	private static final String password = "wudonglong";
	
	private static ArrayList<Connection> conList = new ArrayList<Connection>();//����һ�����ϣ�����Connection�������ӳ�
	
	//�������Connection�����ں��ڵ���
	static {
		for(int i=0;i<5;i++) {
			Connection con = createConnection();
			conList.add(con);
		}
	}
	
	//�������
	public static  Connection getConnection() {
		if(conList.isEmpty() == false) {
			Connection con = conList.get(0);//ȡ������
			conList.remove(con);//����һ�����õ�ʱ�������˾Ͳ�������
			return con;
		}else {
			//����������û�����ӵ�ʱ���´�����������
			return createConnection();
		}
	}
	
	//��������
	private static Connection createConnection() {
		//ע������
		//������
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(connectionUrl, username, password);
		} catch (Exception e) {
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
//		try {
//			if(con!=null)
//			con.close();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
		//��Connection�Ż����ӳؾ͵��ڹ黹
		conList.add(con);
		}
	
}
