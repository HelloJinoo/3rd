package se;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

public class Grade {
	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	String sql ="";
	
	public Grade(){
		
	}
	
	/*교수 - 성적부여*/
	public boolean give_grade(String value[], String subject_number) throws Exception{
		conn = getConnection();
		int flag = 0;
		for(int i=0; i< value.length; i++){
			String sp[] = value[i].split(",");
			String id = sp[0];
			String grade = sp[1];
			sql = "update apply set grade = ? where subject_number = ? && id = ?";
			pstmt = (PreparedStatement) conn.prepareStatement(sql);
			pstmt.setString(1, grade);
			pstmt.setString(2, subject_number);
			pstmt.setString(3, id);

			int result = pstmt.executeUpdate();
			if(result == 1){
				flag =1;
			}
		}
		
		if(flag ==1){
			return true;
		}
		else{
			return false;
		}

	}

	/*학생 - 성적확인 */
	public ResultSet check_grade(String id) throws Exception{
		conn = getConnection();
		sql = "select s.subject_number, s.subject_name , s.grade , a.grade from subject s , apply a where a.id = ? && a.subject_number = s.subject_number;";
		pstmt = (PreparedStatement) conn.prepareStatement(sql);
		pstmt.setString(1, id);
	
		rs = pstmt.executeQuery();
		if( rs.next()){
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
