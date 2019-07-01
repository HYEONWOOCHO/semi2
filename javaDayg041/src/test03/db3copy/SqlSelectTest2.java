package test03.db3copy;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;



class MyFrame extends JFrame{
	//원도창을 구성 컴포넌트
	JTextField id,title,p,year,price,author;
	JButton privBtn,nextBtn,insertBtn,delBtn,searchBtn,clearBtn;
	ResultSet rs;
	Statement stmt;
	
	
	public MyFrame() throws SQLException {
		setTitle("Datebase Viewer");
		Connection con=makeCon(); //연결
		stmt=con.createStatement();
		String sql="select * from books";
		rs.getStatement().executeQuery(sql);
		
		setLayout(new GridLayout(0, 2));
		add(new JLabel("ID", JLabel.CENTER));
		add(id=new JTextField());
		add(new JLabel("TITLE", JLabel.CENTER));
		add(title=new JTextField());
		add(new JLabel("PUBLISHER", JLabel.CENTER));
		add(p=new JTextField());
		add(new JLabel("YEAR", JLabel.CENTER));
		add(year=new JTextField());
		add(new JLabel("PRICE", JLabel.CENTER));
		add(price=new JTextField());
		add(new JLabel("저지검색", JLabel.CENTER));
		add(author=new JTextField());
		
		privBtn=new JButton("privBtn");
		privBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println();
				
			}
		});
		
		setSize(350, 200);
		setVisible(true);
		
	}

	private Connection makeCon() {
		//db연결
		String url="jdbc:oracle:thin:@localhost:1521:orcl";
		String user="scott";
		String pass="123456";
		Connection con=null;
		
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 적재성공");
			con=DriverManager.getConnection(url, user, pass);
			System.out.println("데이터 베이스 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return con;
	}
}


public class SqlSelectTest2 {
	public static void main(String[] args) throws SQLException {
		new MyFrame();
	}



}
