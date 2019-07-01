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
			System.out.println("드라이버 적재성공");
			con=DriverManager.getConnection(ur1, id, pass);
			System.out.println("데이터 베이스 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		return null;
	}
	

}
