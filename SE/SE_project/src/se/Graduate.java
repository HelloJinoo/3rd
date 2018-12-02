package se;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Graduate{
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql ="";
	
	public Graduate(){
		
	}
	
	/* 학생 - 졸업자가진단*/
	public ResultSet subject_totalcount(String id) throws Exception{
		
		conn = getConnection();
		sql = "select count(*) from subject , apply where apply.id = ? && apply.subject_number = subject.subject_number && apply.score !='0' ";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			return rs;
		}
		else{
			return rs;
		}
		
	}
	public Connection getConnection() throws Exception{
		String jdbcUrl = "jdbc:mysql://localhost:3306/se_term";
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection(jdbcUrl, "root", "asdasd1");
		return conn;
	}
}
