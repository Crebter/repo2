package com.sikiedu.jdbc01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDemo01 {
	public static void main(String[] args) {
		
		//selectAll();
		
		//��½У��
		//sqlע��
//			System.out.println(selectByUsernamePassword("maikou", "123"));
//			System.out.println(selectByUsernamePassword("asd", "asdas' or '1'='1"));//1=1���������
//			System.out.println("���sqlע��");
//			System.out.println(selectByUP2("asd", "asdas' or '1'='1"));
//			System.out.println(selectByUP2("maikou", "123"));		
//			selectUserByPage(3, 4);
//			insert("ads", "wudonglong");
			//delete(73);
			//update(72,"wu");
			transferAccounts("�ⶫ��", "������", 500);
	}
	
	//�г����е��û�
	public static void selectAll() { 
		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;
		//ע������
		try {
			con = JDBCUtils.getConnection();
			
			//�����ݿⷢ���ѯ����
			stmt = con.createStatement();//����ֵ��ֵ��result
			
			/*�����*/rs = stmt.executeQuery("select * from user");
			//�õ�����
			while(rs.next()) {
				System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
				System.out.println(rs.getInt("id")+","+rs.getString("username")+","+rs.getString("password"));
				
//				rs.getInt(1);//��һ�У��õ�ID��
//				rs.getString(2);//�ڶ��У��õ�username;
			}
			
			
			//�ȴ򿪺�ر�
			
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}finally {
			//��÷ֿ�try catch   ��Ȼ��һ�������⣬������Ҳ�رղ���
				JDBCUtils.close(rs, stmt, con);
		}
		
		
	}


	//��½У��(sqlע��)
	public static boolean selectByUsernamePassword(String username,String password) {
		Connection con = null;
		Statement stmt =null;
		ResultSet rs = null;
		
		try {
			con = JDBCUtils.getConnection();
			
			stmt = con.createStatement();
			String sql="select * from user where username = '"+username+"' and password = '"+password+"'";//�����ݿ�string������Ҫ�ӵ�����
			System.out.println();//�ȴ���statement��ִ��sql���
			rs = stmt.executeQuery(sql);
			
			
			//ֻ��һ����¼
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally {
			//��÷ֿ�try catch   ��Ȼ��һ�������⣬������Ҳ�رղ���
			JDBCUtils.close(rs, stmt, con);
				
		}
		
			return false;//������try catch�Ĵ��ڣ����ǰ���д�������ˣ����޷�����һ���������ʴ���Ҫ���أ���֤�����ܷ��ض���
		
		
	}
	
	
	
	//��½У��(���sqlע��)
	public static boolean selectByUP2(String username,String password) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			//ע������
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			
		//������
			String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
			con = DriverManager.getConnection(url, "root", "wudonglong");
			
			//���ṩsql��������ò���
			String sql = "select * from user where username = ? and password = ?";
			pstmt = con.prepareStatement(sql);
			
			
			//����������������ƴ�ַ�
			pstmt.setString(1, username);//�� 1 ��ʼ
			pstmt.setString(2, password);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}else {
				return false;
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//��÷ֿ�try catch   ��Ȼ��һ�������⣬������Ҳ�رղ���
		
				try {
					if(rs!=null)
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					if(pstmt!=null)
					pstmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					if(con!=null)
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
			return false;//������try catch�Ĵ��ڣ����ǰ���д�������ˣ����޷�����һ���������ʴ���Ҫ���أ���֤�����ܷ��ض���
	}
	
	
	//��ҳ��ѯ
	//pageNumber��ҳ�����ڼ�ҳ       pageCount��ÿҳ��ʾ����������
	public static void selectUserByPage(int pageNumber,int pageCount) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		//ע������
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");//ʹ��ʲô�����������ݿ�
			//String url = "jdbc:mysql://localhost:3306/web01";//��ʱ���������룬�ƶ��������ݿ�ı���
			String url = "jdbc:mysql://localhost:3306/web01?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
			String user = "root";
			String password = "wudonglong";
			con = DriverManager.getConnection(url, user, password);//�������ӣ�ʹ������
			
			//�����ݿⷢ���ѯ����
			
			stmt = con.prepareStatement("select * from user limit ?,?");
			stmt.setInt(1,(pageNumber-1)*pageCount);
			stmt.setInt(2, pageCount);
			/*�����*/rs = stmt.executeQuery();
			//�õ�����
			while(rs.next()) {
				//System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3));
				System.out.println(rs.getInt("id")+","+rs.getString("username")+","+rs.getString("password"));
				
//				rs.getInt(1);//��һ�У��õ�ID��
//				rs.getString(2);//�ڶ��У��õ�username;
			}
			
			
			//�ȴ򿪺�ر�
			
			
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			//��÷ֿ�try catch   ��Ȼ��һ�������⣬������Ҳ�رղ���
			
				try {
					if(rs!=null)
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					if(stmt!=null)
					stmt.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
				try {
					if(con!=null)
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
	}
	//��������
	public static void insert(String username,String password) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "insert into user(username,password) values(?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			int result = stmt.executeUpdate();//����ֵ�����ܵ�Ӱ�������
			System.out.println("����ɹ���");
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, stmt, con);
		}
	}
	//ɾ������
	public static void delete(int id) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "delete from user where id = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, id);
			int result = stmt.executeUpdate();//����ֵ�����ܵ�Ӱ�������
			if(result>0) {
				System.out.println("ɾ���ɹ�");
			}else {
				System.out.println("ɾ��ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, stmt, con);
		}
	}
	//�޸�����
	public static void update(int id,String newPassword) {
		Connection con = null;
		PreparedStatement stmt1 = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			String sql = "update user set password = ? where id = ?";
			stmt1 = con.prepareStatement(sql);
			stmt1.setString(1, newPassword);
			stmt1.setInt(2, id);
			int result = stmt1.executeUpdate();//����ֵ�����ܵ�Ӱ�������
			if(result>0) {
				System.out.println("�޸ĳɹ�");
			}else {
				System.out.println("�޸�ʧ��");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(rs, stmt1, con);
		}
	}

	public static void transferAccounts(String username1,String username2,int money) {
		Connection con = null;
		PreparedStatement stmt1 = null;
		PreparedStatement stmt2 = null;
		ResultSet rs = null;
		try {
			con = JDBCUtils.getConnection();
			
			con.setAutoCommit(false);//��������
			
			String sql = "update user set balance = balance - ? where username = ?";
			stmt1 = con.prepareStatement(sql);
			stmt1.setInt(1, money);
			stmt1.setString(2, username1);
			stmt1.executeUpdate();
			
//			String s = null;//��ʽ�쳣
//			s.charAt(2);
			
			sql = "update user set balance = balance + ? where username = ?";
			stmt2 = con.prepareStatement(sql);
			stmt2.setInt(1, money);
			stmt2.setString(2, username2);
			stmt2.executeLargeUpdate();
			
			con.commit();//�ύ����
			
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtils.close(stmt2, stmt1, con);
		}
	}
}	