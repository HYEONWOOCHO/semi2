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
			System.out.println("드라이버 적재성공");
			con=DriverManager.getConnection(url, user, pass);
			System.out.println("데이터 베이스 연결 성공");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
		
		
	}
		public static void main(String[] args) throws SQLException {
			Connection con=makeConnection();
			
			
			//조건수정
			//year가 2002거나 가격이 35000이상인 레코드를 출력
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
