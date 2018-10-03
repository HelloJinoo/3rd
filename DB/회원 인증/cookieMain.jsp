<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <% 
    	String id="";
    	try{
    		Cookie[] cookies = request.getCookies();
    		if(cookies != null){
    			for(int i=0; i<cookies.length; ++i){
    				if(cookies[i].getName().equals("id")){
    					id=cookies[i].getValue();
    				}
    			}
    			if(id.equals("")){
    				response.sendRedirect("loginForm.jsp");
    			}
    		}
    		else{
    			response.sendRedirect("loginForm.jsp");
    		}
    	}
    	catch(Exception e){}
    
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>쿠키를 사용한 간단한 회원 인증</title>
</head>
<body>
	<b><%=id%></b>님이 로그인 하셨습니다.
	<form method ="post" action="cookieLogout.jsp">
		<input type="submit" value="로그아웃">
	</form>
</body>
</html>