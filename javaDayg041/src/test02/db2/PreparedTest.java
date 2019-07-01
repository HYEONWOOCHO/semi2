package test02.db2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PreparedTest {
   private static Connection makeConnection() {
      String url="jdbc:oracle:thin:@localhost:1521:orcl";
      String user="scott";
      String pass="123456";
      Connection con=null;
      
      try {
         Class.forName("oracle.jdbc.driver.OracleDriver");
         System.out.println("���� ����");
         con=DriverManager.getConnection(url, user, pass);
         System.out.println("���� ����");
      } catch (Exception e) {
         System.out.println("���� ����");
         // TODO Auto-generated catch block
         e.printStackTrace();
      }
      
      return con;
   }
   
   
   public static void main(String[] args) throws SQLException {
      Connection con =makeConnection();
      //���Ǽ���
//      year�� 2002 �̰ų� ������ 35000�̻��� ���ڵ带 ���
      String sql=
            "select title,price from books where publisher=? and year=?";
      PreparedStatement pstmt=con.prepareStatement(sql);
      pstmt.setString(1, "OREILLY");
      pstmt.setString(2, "2009");
      
      ResultSet rs=pstmt.executeQuery();
      while (rs.next()) {
         String title=rs.getString("title");
         int price=rs.getInt("price");
         System.out.println(title+" "+price);
      }
   }


}
