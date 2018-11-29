<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<%@page import="se.Subject" %>
</head>
<body>
	<%
	
		String id= (String)session.getAttribute("id");
		String sub_number = request.getParameter("subject_num");
		
		Subject s = new Subject();
		boolean result = s.apply_subject(id, sub_number);
		if( result == true){
			%>
				<script>	
					alert("수강신청되었습니다.");
					location.href="after_login_studentMain.jsp";
				</script>
			<% 
		}
		else{
			%>
			<script>	
					alert("이미 신청한 강의입니다.");
					Locaiont.href="apply_subject.jsp";
				</script>
			<% 
		}
	%>
	
	
</body>
</html>