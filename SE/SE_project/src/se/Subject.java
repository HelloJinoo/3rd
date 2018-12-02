package se;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Subject {

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql ="";
	
	public Subject(){
		
	}
	/*전체 - 수강편람 조회*/
	public ResultSet show_Allsubject() throws Exception{
		conn = getConnection();
		sql = "select * from subject";
		//sql = " select count(*) ,subject.subject_number , subject.subject_name ,subject.professor_name, subject.time,subject.day,subject.grade , subject.place, subject.personnel from subject , apply where subject.subject_number = apply.subject_number";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
	
		if(rs.next()){
			rs.previous();
			return rs;
		}
		return rs;
	}
	
	/*교수 - 강의 등록*/
	public boolean regist_subject(String id ,String subejct_name , String professor_name ,int grade, int personnel,String time, String day , String place) throws Exception{
		String subject_number = "18-"+(find_subjectcount()+1);
		conn = getConnection();
		sql = "insert into subject values(?,?,?,?,?,?,?,?)";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, subject_number);
		pstmt.setString(2, subejct_name);
		pstmt.setString(3, professor_name);
		pstmt.setString(4, time);
		pstmt.setString(5, day);
		pstmt.setInt(6, grade);
		pstmt.setString(7, place);
		pstmt.setInt(8,personnel);
		int result = pstmt.executeUpdate();
		
		sql = "insert into regist values(?,?)";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, subject_number);
		int result2 = pstmt.executeUpdate();
		if(result == 0 || result2 ==0){
			return false;
		}
		else{
			return true;
		}
		
		
	}
	/*학생 - 강의신청*/
	public boolean apply_subject(String id , String subject_num) throws Exception{
		if(!check_mysubject(id, subject_num)){
			conn = getConnection();
			sql = "insert into apply values(?,?,?,?)";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, subject_num);
			pstmt.setString(3, "");
			pstmt.setInt(4,0);
			int result = pstmt.executeUpdate();
			return true;
		}
		else{
			return false;
		}
	}
	
	/*신청 강의가 있는지 확인*/
	private boolean check_mysubject(String id , String subject_num) throws Exception{
		conn = getConnection();
		sql = "select * from apply where id =? && subject_number = ?";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		pstmt.setString(2, subject_num);
		rs = pstmt.executeQuery();
		if( rs.next()){
			return true;
		}
		else{
			return false;
		}
	}
	/*시간표 확인*/
	public ResultSet view_timetable(String id) throws Exception{
		conn = getConnection();
		sql = "select * from subject , apply where apply.id = ? && apply.subject_number = subject.subject_number";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			rs.previous();
			return rs;
		}
		else{
			return rs;
		}
		
	}
	/* 학생 - 수강취소 */
	public boolean cancel_subject(String id , String subject_num) throws Exception{
			conn = getConnection();
			sql = "delete from apply where id =? && subject_number = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, subject_num);
			int result = pstmt.executeUpdate();
			if( result == 1 ){
				return true;
			}
			else{
				return false;
			}
	}
	/*교수 - 자신의 강의 목록*/
	public ResultSet show_mylecture(String id) throws Exception{
		conn = getConnection();
		sql = "select * from regist , subject where regist.id = ? &&regist.subject_number = subject.subject_number";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
		rs = pstmt.executeQuery();
		if(rs.next()){
			rs.previous();
			return rs;
		}
		else{
			return rs;
		}
	}
	
	
	/*등록된 강의 수*/
	private int find_subjectcount() throws Exception {
		conn = getConnection();
		sql = "select count(*) from subject";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		rs = pstmt.executeQuery();
		if(rs.next()){
			return Integer.parseInt(rs.getString("count(*)"));
		}
		else{
			return Integer.parseInt(rs.getString("count(*)"));
		}
	}
	public Connection getConnection() throws Exception{
		String jdbcUrl = "jdbc:mysql://localhost:3306/se_term";
		Class.forName("com.mysql.jdbc.Driver");
		conn = (Connection) DriverManager.getConnection(jdbcUrl, "root", "asdasd1");
		return conn;
	}
}
