<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@page import="se.Grade" %>
  <%@page import="java.sql.ResultSet" %>  
</head>

<body>
	<% 
		Grade g = new Grade();
		String id = (String)session.getAttribute("id");
		ResultSet rs = g.check_grade(id);
		String score="";
		if(rs.next()){
			rs.previous();
		%>
		
		<table class="myinformation" >
				<tr>
					<th>
						과목번호
					</th>
					<th>
						과목명
					</th>
					<th>
						학점
					</th>
					<th>
						성적
					</th>
					<th>
						평점
					</th>
				</tr>
		<% 
		while(rs.next()){
			String grade = rs.getString("a.grade");
			if(grade.equals("A") ){
				score= "4";
			}
			else if(grade.equals("B") ){
					score="3";
			}
			else if(grade.equals("C") ){
					score= "2";
				}
			else if(grade.equals("D") ){
					score= "1";
				}
			else if(grade.equals("F") ){
					score= "0";
			}
		
	
	%>
			<tr>
				<td>
				<%=  rs.getString("subject_number")%>
				</td>
				<td>
				<%= rs.getString("subject_name")%>
				</td>
				<td>
				<%= rs.getString("s.grade")%>
				</td>
				<td>
				<%= rs.getString("a.grade")%>
				</td>
				<td>
					<%=score %>
				</td>
				
			</tr>
		<%} %>
			</table>
			<%}
				else{
			%>
				<script>
					alert("신청한 강의가 없습니다.");
					location.href="after_login_studentMain.jsp";
				</script>
			<% }
			%>
	
</body>
</html>