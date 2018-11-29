<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     <%@page import="java.sql.ResultSet" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ page import ="se.Subject" %>
<%@ page import ="java.util.ArrayList"%>
<link rel="stylesheet" href="Main.css">
<title>Insert title here</title>
</head>
<body>
	<%
		Subject s = new Subject();
		ResultSet rs = s.show_Allsubject();
		
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
						교수
					</th>
					<th>
						학년
					</th>
					<th>
						인원
					</th>
					<th>
						시간
					</th>
					<th>
						요일
					</th>
					<th>
						장소
					</th>
				</tr>
		<% 
		while(rs.next()){
			
	%>
			<tr>

				<td>
				<%=  rs.getString("subject_number")%>
				</td>
				<td>
				<%= rs.getString("subject_name")%>
				</td>
				<td>
				<%=  rs.getString("professor_name")%>
				</td>
				<td>
				<%= rs.getString("grade")%>
				</td>
				<td>
				 <%= rs.getString("personnel")%>
				</td>
				<td>
				<%= rs.getString("time")%>
				</td>
				<td>
				<%= rs.getString("day")%>
				</td>
				<td>
				<%= rs.getString("place")%>
				</td>
				
			</tr>
		<%} %>
			</table>
			<%}
				else{
			%>
				<script>
					alert("등록된 강의가 없습니다.");
					location.href="after_login_studentMain.jsp";
				</script>
			<% }%>
</body>
</html>