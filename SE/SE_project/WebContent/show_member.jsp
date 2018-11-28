<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src = "function.js"></script>
<%@page import="se.Member" %>
    <%@page import="java.sql.ResultSet" %>  
</head>
<body>
<% 
			Member m = new Member();
			ResultSet rs = m.show_member();
		%>
		
		<h2>회원 전체 조회</h2>
		<table class="myinformation" >
			<tr>
				<th>
					선택
				</th>
				<th>
					아이디
				</th>
				<th>
					비밀번호
				</th>
				<th>
					주소
				</th>
				<th>
					휴/복여부
				</th>
				<th>
					권한
				</th>
			</tr>
			<%
				while(rs.next()){
			%>
			<tr>
				<td>
					<input type ="button" value ="삭제" onClick="location.href='delete_member.jsp?id=<%=rs.getString("id") %>'">
				</td>
				<td>
					<%=rs.getString("id")%> 
				</td>
				<td>
					 <%=rs.getString("password")%> 
				</td>
				<td>
					<%=rs.getString("address")%> 
				</td>
				<td>
					<%
					int current_value = rs.getInt("current") ;
					int autority_value = rs.getInt("authority");
					 if( autority_value ==1 && current_value == 1 ){  //학생 , 재학
						 %>
							재학중
					 <%
					 }
					 else if(autority_value ==1  && current_value == 0  ){  //학생 휴학
						 %>
						 	휴학중
						 <% 
					 }
					 else if(autority_value ==2 &&current_value == 1){ //교수 재직
						 %>
						 	재직중
						 <% 
					 }
					 else if( autority_value ==2 && current_value == 0  ){ //교수 휴직
						 %>
						 	휴직중
						 <%
					 }
					 
					 %>
				</td>
				<td>
				<% if(autority_value == 0 ){ %>
						관리자
						<%} else if(autority_value == 1 ){
							%> 학생
						<% }
						else if( autority_value == 2 ){
							%>
							교수
			<%
						}
						%>
					
					
				</td>
			</tr>
		<%}
			%>
			
		</table>
</body>
</html>