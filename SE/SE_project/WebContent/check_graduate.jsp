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
	<%
			int graduate_count=100;
			Subject s = new Subject();
			String id = (String)session.getAttribute("id");
			ResultSet rs = s.view_timetable(id);
			ResultSet count = s.subject_totalcount(id);
		%>
		
		<h2>수강 내역</h2>
		<table class="myinformation" >
			<tr>
				<th>
					과목번호
				</th>
				<th>
					과목명
				</th>
				<th>
					교수명
				</th>
				<th>
					시간
				</th>
				<th>
					날짜
				</th>
				<th>
					학년
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
					<%=rs.getString("subject_number")%> 
				</td>
				<td>
					 <%=rs.getString("subject_name")%> 
				</td>
				<td>
					<%=rs.getString("professor_name")%> 
				</td>
				<td>
					<%=rs.getString("time")%> 
				</td>
				<td>
					<%=rs.getString("day")%> 
				</td>
				<td>
					<%=rs.getString("grade")%> 
				</td>
				<td>
					<%=rs.getString("place")%> 
				</td>
			</tr>
		<%}
			%>
			
		</table>
		
		<%	int mytotal_grade = (Integer.parseInt(count.getString("count(*)") )*3); %>
				<h3>현재 총 이수학점 : <%=mytotal_grade%> <br/> 졸업가능 학점 : <%=graduate_count%><h3>
		<%
	
				if(graduate_count < mytotal_grade ){
					%>
						<h2>졸업 가능</h2>
						
					<% 
				}
				else{
					%>
					<h2>졸업 불가능</h2>
				
				<% 
				}
		%>
			
</body>
</html>