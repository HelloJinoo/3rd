<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<%@page import="se.Scholarship" %>
 <%@page import="java.sql.ResultSet" %>  
</head>
<body>
	<% 
		Scholarship s = new Scholarship();
		String id = (String)session.getAttribute("id");
		ResultSet rs = s.check_scholarship(id);
		
		if(rs.next()){
			String kind = rs.getString("scholarship_kind");	
			if(kind.equals("total")){
				%>
					<script>
					alert("전액장학금입니다.");
					</script>
				<% 
			}
			else if(kind.equals("portion") ){
				%>
				<script>
				alert("부분(50%) 장학금입니다.");
				</script>
			<% 
			}
			else{%>
				<script>
				alert("장학금대상에 선택되지않습니다.");
				</script>
			<% 	
			}
		}
		else{
			%>
			<script>
			alert("장학금등록이 되어있지않습니다.");
			</script>
			<% 	
		}
		%>
			
			<script>
			location.href="after_login_studentMain.jsp";
			</script>
				
	
	

</body>
</html>