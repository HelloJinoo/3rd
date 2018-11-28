<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  <%@page import="java.sql.ResultSet" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <%@ page import ="se.Member,com.mysql.jdbc.Connection" %>
  <link rel="stylesheet" href="Main.css">
<title>Insert title here</title>
</head>
<body>
		<% 
			Member m = new Member();
			ResultSet rs = m.show_myinformation( (String)session.getAttribute("id"));
		%>
		
		<h2>수강편람조회</h2>
	<form action = "modify_information.jsp" method ="post">
		<table class="myinformation" >
			<tr>
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
			</tr>
			<tr>
				<td>
					<input type="text" name="id" value= <%=rs.getString("id")%> disabled="true" >
				</td>
				<td>
					<input type="password" name="pwd" value= <%=rs.getString("password") %> >
				</td>
				<td>
					<input type="text" id="information_addr" name="addr" value=<%=rs.getString("address")%> >
				</td>
				<td>
					<%
					int current_value = rs.getInt("current") ;
					int autority_value = rs.getInt("authority");
					 if( autority_value ==1 && current_value == 1 ){  //학생 , 재학
						 %>
							<select name ="current">
								<option value="1">재학중</option>
								<option value="0">휴학</option>
							</select>
					 <%
					 }
					 else if(autority_value ==1  && current_value == 0  ){  //학생 휴학
						 %>
						 	<select name ="current">
								<option value="0">휴학중</option>
								<option value="1" >재학</option>
							</select>
						 <% 
					 }
					 else if(autority_value ==0 &&current_value == 1){ //교수 재직
						 %>
						 	<select name ="current">
								<option value="1">재직중</option>
								<option value="0">휴직</option>
							</select>
						 <% 
					 }
					 else if( autority_value ==0 && current_value == 0  ){ //교수 휴직
						 %>
						 	<select name ="current">
								<option value="0" >휴직중</option>
								<option value="1" >재직</option>
							</select>
						 <%
					 }
					 %>
				</td>
			</tr>

			<input type="submit" value="개인정보변경">
		</table>
	</form>
	
	
</body>
</html>