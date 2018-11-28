<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<%@page import ="se.Subject" %>
<body>
<%		
		String sub_name= request.getParameter("sub_name");
		String pro_name= request.getParameter("pro_name");
		String grade = request.getParameter("grade");
		String time= request.getParameter("time");
		String day= request.getParameter("day");
		String place= request.getParameter("place");
		Subject s = new Subject();
		boolean result = s.regist_subject(sub_name, pro_name, Integer.parseInt(grade), time, day, place);
		if( result == true){
			%>
			<script>
			alert("강의 등록 성공");
			location.href ="after_login_professorMain.jsp";
			</script>
			<% 
			
		}
		else{
			%>
			<script>
			alert("강의 등록 실패");
			location.href ="after_login_professorMain.jsp";
			</script>
			<% 
		}
	%>
	
</body>
</html>