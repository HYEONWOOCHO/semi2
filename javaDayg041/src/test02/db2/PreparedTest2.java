package test02.db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedTest2 {
//	Connection con=makeConnection();
	
	private static Connection makeConnection() {
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pass="123456";
		Connection con= null;
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("����̹� ���缺��");
			con=DriverManager.getConnection(url, user, pass);
			System.out.println("������ ���̽� ���� ����");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
		
	}
		public static void main(String[] args) throws SQLException {
			Connection con=makeConnection();
			
			
			//���Ǽ���
			//year�� 2002�ų� ������ 35000�̻��� ���ڵ带 ���
			String sql = 
				"select title,price from books where price>=? and year=?";
			PreparedStatement pstmt=con.prepareStatement(sql);
			
			pstmt.setInt(1,35000);
			pstmt.setInt(2, 2002);
			
			ResultSet rs=pstmt.executeQuery();
			while (rs.next()) {
				
				String title=rs.getString("title");
				String year=rs.getString("year");
				int price=rs.getInt("price");
				System.out.println(title+" "+price + year);
				
				
			}
			
		}

}
