package se;

import java.sql.DriverManager;
import java.sql.ResultSet;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Member {

	

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql ="";
	
	public Member(){
		
	}
	
	
	/*회원 - 개인정보조회*/
	public ResultSet show_myinformation(String id) throws Exception{
		boolean login_confirm = false;
		conn = getConnection();
		sql = "select * from member where id = ?";
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
	
	
	/*회원 - 개인정보수정*/
	public int modify_information(String id , String password , String address ,String current) throws Exception{
		conn = getConnection();
		sql = "update member set password =? , address = ? , current =? where stdNo = ?";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, password);
		pstmt.setString(2, address);
		pstmt.setInt(3, Integer.parseInt(current));
		pstmt.setString(4, id);
		int result = pstmt.executeUpdate();
		if(result == 0){
			return 0;
		}
		else{
			return 1;
		}
	}
	/*관리자 - 전체 회원조회*/
	public ResultSet show_member() throws Exception{
		conn = getConnection();
		sql = "select * from member";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		
		if(rs.next()){
			rs.previous();
			return rs;
		}
		else{
			return rs;
		}
		
	}
	
	/*관리자 - 회원등록*/
	public boolean regist_member(String id , String password, String address , int current , int authority) throws Exception{
		conn = getConnection();
		sql = "insert into member values(?,?,?,?,?)";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, password);
		pstmt.setString(3, address);
		pstmt.setInt(4, current);
		pstmt.setInt(5, authority);
		int result = pstmt.executeUpdate();
		if(result == 0){
			return false;
		}
		else{
			return true;
		}
	}
	
	/*관리자 - 회원삭제*/
	public boolean delete_member(String id) throws Exception{
		conn = getConnection();
		sql = "delete from member where id = ?";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		int result = pstmt.executeUpdate();
		
		if( result == 0){
			return false;
		}
		else{
			return true;
		}
		
	}
	
	public Connection getConnection() throws Exception{
		String jdbcUrl = "jdbc:mysql://localhost:3306/se_term";
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection(jdbcUrl, "root", "asdasd1");
		return conn;
	}
}
