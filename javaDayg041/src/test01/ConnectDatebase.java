package test01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectDatebase {
	public static void main(String[] args) {
		Connection con=makeConnection();
		
	}

	private static Connection makeConnection() {
		String ur1="jdbc:oracle:thin:@localhost:1521:orcl";
		String id="scott";
		String pass="123456";
		Connection con=null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� ���缺��");
			con=DriverManager.getConnection(ur1, id, pass);
			System.out.println("������ ���̽� ���� ����");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}
	

}
