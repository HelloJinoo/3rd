<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@page import="se.Subject" %>
 <%@page import="java.sql.ResultSet" %>  
</head>
<body>
	<h2>성적부여 화면</h2>
	<% 
		Subject s = new Subject();
		String id = (String)session.getAttribute("id");
		ResultSet rs = s.show_mylecture(id);
		if(rs.next()){
			rs.previous();
		%>
		
		<table class="myinformation" >
				<tr>
					<th>
						선택
					</th>
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
				</tr>
		<% 
		while(rs.next()){
	%>
			<tr>
				<td>
				<input type ="button" value ="학생_성적" onClick="location.href='give_grades.jsp?subject_num=<%=rs.getString("subject_number") %>'">
				</td>
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
				
			</tr>
		<%} %>
			</table>
			<%}
				else{
			%>
				<script>
					alert("등록된 강의가 없습니다.");
					location.href="after_login_professorMain.jsp";
				</script>
			<% }%>
</body>
</html>