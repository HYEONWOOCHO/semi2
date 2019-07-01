package testg06.member;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.*;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;





public class MemberTest extends JFrame implements ActionListener {
	JTable table;
	JTextField namef;
	JTextField scoref;
	JButton button;

	MyTableModel model;

	public MemberTest() {
		super("명예의 전당");
		setSize(600, 300);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		model = new MyTableModel();
		model.fillTable();
		JLabel label = new JLabel("명예의 전당", JLabel.CENTER);
		label.setFont(new Font("SansSerif", Font.PLAIN, 30));
		add(label, BorderLayout.NORTH);

		table = new JTable(model);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setPreferredSize(new Dimension(500, 200));
		add(scrollPane, BorderLayout.CENTER);

		JPanel panel = new JPanel();

		panel.add(new JLabel("이름"));
		namef = new JTextField(10);
		panel.add(namef);

		panel.add(new JLabel("점수"));
		scoref = new JTextField(10);
		panel.add(scoref);

		button = new JButton("점수 제출");
		button.addActionListener(this);
		panel.add(button);

		add(panel, BorderLayout.SOUTH);
		setVisible(true);

	}

	@Override
	public void actionPerformed(ActionEvent event) {
		String name = namef.getText();
		String score = scoref.getText();

		try {
			Connection conn = makeConnection();
			Statement stmt = (Statement) conn.createStatement();
			 stmt.executeUpdate("INSERT INTO scores VALUES ('" + name + "'," + score + ")");
			conn.close();
		} catch (Exception e) {
			System.err.println("Caught Exception1: " + e.getMessage());
		}
		model.fillTable();
	}

	public static Connection makeConnection() {
		String url = "jdbc:oracle:thin:@localhost:1521:orcl";
		String id = "scott";
		String password = "123456";
		Connection con = null;

		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 적재 성공");
			con = DriverManager.getConnection(url, id, password);
			System.out.println("데이터 베이스 연결 성공");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println(" 드라이버를 찾을수 없습니다");
		}

		return con;
	}

	public static void main(String[] args) {
		MemberTest mainApp = new MemberTest();
	}

	public class MyTableModel extends AbstractTableModel {

		private String[] columnNames = { "이름", "점수" };
		private static final int ROWS = 10;
		private static final int COLS = 2;
		Object[][] data = new String[ROWS][COLS];

		@Override
		public int getColumnCount() {

			return columnNames.length;
		}

		@Override
		public int getRowCount() {
			// TODO Auto-generated method stub
			return data.length;
		}

		@Override
		public String getColumnName(int col) {
			// TODO Auto-generated method stub
			return columnNames[col].toString();
		}

		public void fillTable() {
			Connection conn;
			try {
				conn = makeConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt
						.executeQuery("select * from (select * from scores) where ROWNUM <=3 ORDER BY SCORES DESC");

				int row = 0;
				while (rs.next()) {
					data[row][0] = rs.getString("name");
					data[row][1] = rs.getString("scores");
					row++;
				}
				conn.close();
			} catch (SQLException e) {
				System.err.println("Caught Exception2:" + e.getMessage());
			}
			fireTableDataChanged();
		}

		public void setValueAt(Object value, int row, int col) {
			data[row][col] = value;
			fireTableCellUpdated(row, col);
		}

		public Object getValueAt(int row, int col) {
			return data[row][col];
		}
	}

}