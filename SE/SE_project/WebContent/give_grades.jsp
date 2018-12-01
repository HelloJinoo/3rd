<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@page import="se.Subject" %>
<%@page import="se.Member" %>
<%@page import="se.Grade" %>
 <%@page import="java.sql.ResultSet" %>  
</head>
<body>
	
	<% 
		String subject_number = request.getParameter("subject_num");
		Member m = new Member();
		Grade g = new Grade();
		
		ResultSet rs = m.apply_student(subject_number);

		if(rs.next()){
			rs.previous();
		%>
		<form action ="give_grades2.jsp">
		<table class="myinformation" >
				<tr>
					<th>
						학번
					</th>
					<th>
						선택
					</th>
				
				</tr>
		<% 
		while(rs.next()){
	%>
			<tr>
				<td>
				<%=  rs.getString("id")%>
				</td>
				<td>
					<select name ="grade">
								<option value="<%= rs.getString("id") %>,A">A</option>
								<option value="<%= rs.getString("id") %>,B" >B</option>
								<option value="<%= rs.getString("id") %>,C">C</option>
								<option value="<%= rs.getString("id") %>,D">D</option>
								<option value="<%= rs.getString("id") %>,F">F</option>
					</select>
				</td>
			</tr>
		<%} %>
			</table>
			<input type="text" value="<%=subject_number%>" name="subject_number" hidden >
			<input type="submit" value="부여">
			</form>
			
			<%}
				else{
			%>
				<script>
					alert("수강한 학생이 없습니다.");
					location.href="after_login_professorMain.jsp";
				</script>
			<% }%>
		

</body>
</html>