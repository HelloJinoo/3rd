package se;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Scholarship {
	
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql ="";
	
	public Scholarship(){
		
	}
	
	/*관리자 - 전체 학생의 성적 보여주기*/
	public ResultSet manage_scholarship() throws Exception{
		conn = getConnection();
		sql ="select id,sum(score) , count(*) from apply group by id order by sum(score)";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if( rs.next() ){
			rs.previous();
			return rs;
		}
		else{
			return rs;
		}
		
	}
	
	/*관리자 - 전체 학생의 성적부여*/
	public boolean give_scholarship(String id , String kind) throws Exception{
		conn = getConnection();
		sql ="insert into scholarship values(? , ?)";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, kind);
		int result = pstmt.executeUpdate();
		if( result == 1 ){
			return true;
		}
		else{
			return false;
		}
		
	}
	
	/*학생 - 장학여부 확인*/
	public ResultSet check_scholarship(String id) throws Exception{
		conn = getConnection();
		sql ="select * from scholarship where id =?";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		 rs = pstmt.executeQuery();
		if( rs.next()){
			rs.previous();
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
