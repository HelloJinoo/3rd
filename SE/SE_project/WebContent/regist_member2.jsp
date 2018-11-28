
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <%@ page import ="se.Member" %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% 
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");
		String addr = request.getParameter("addr");
		String current = request.getParameter("current");
		String authority = request.getParameter("authority");
		
		Member m = new Member();
		boolean result =m.regist_member(id, pwd, addr, Integer.parseInt(current), Integer.parseInt(authority));
		if( result == true){
			%>
			<script>
				alert("회원 등록 성공");
				Location.href="after_login_managerMain.jsp";
			</script>
			<% 
		}
		else{
			%>
			<script>
			alert("회원 등록 실패");
			Location.href="after_login_managerMain.jsp";
			</script>
			<%
		}
		
		
	
	%>
</body>
</html>