package test01;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlSelectTest {
	public static void main(String[] args) throws SQLException {
		Connection con=makeConnection();
		Statement stmt=con.createStatement();
		String sql="select * from books";
		ResultSet rs=stmt.executeQuery(sql);
		
		while (rs.next()) {
			int book_id=rs.getInt(1);
			String title=rs.getString(1);
			String PUBLUSHER=rs.getString(1);
			String year=rs.getString(1);
			int price=rs.getInt(1);
			
			//출력
			System.out.println(book_id+" "+title+" "+PUBLUSHER+" "+year+" "+ price+ " ");
		}
	}

	private static Connection makeConnection() {
		String url="jdbc.oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pass="123456";
		Connection con=null;
		
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 적재성공");
			con=DriverManager.getConnection(url, user, pass);
			System.out.println("데이터 베이스 연결성공");
		} catch (Exception e) {
			
			System.out.println("연결실패");
		}
		return con;
		
	}
}

