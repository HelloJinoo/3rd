<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>웹브라우저에 저장된 쿠키를 가져오는 페이지</title>
</head>
<body>
	<h2>웹 브라우저에 저장된 쿠키를 가져오는 페이지</h2>
	<%
		Cookie[] cookies = request.getCookies();
		if(cookies != null){
			for(int i=0; i< cookies.length; ++i){
				if(cookies[i].getName().equals("id")){
			%>
				쿠킥의 이름은 "<%=cookies[i].getName() %>"이고
				쿠키의 값 "<%=cookies[i].getValue() %>"입니다.
			<% 	
				}
			}
			
		}
	%>
</body>
</html>