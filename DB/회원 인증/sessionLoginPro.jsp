<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@page import ="login.LogonDBBean" %>
    <%request.setCharacterEncoding("euc-kr"); %>
    <%
    	String id = request.getParameter("id");
    	String passwd = request.getParameter("passwd");
    	
    	LogonDBBean logon = LogonDBBean.getInstatnce();
    	int check = logon.userCheck(id, passwd);
    	
    	if(check == 1){
    		session.setAttribute("id", id);
    		response.sendRedirect("sessionMain.jsp");		
    	}
    	else if( check ==0){%>
    	<script>
    		alert("비밀번호가 맞지 않습니다.");
    		history.go(-1);
    	</script>
    	<% }else{%>
    	<script>
    		alert("아이디가 맞지 않습니다.");
    		history.go(-1);
    	</script>
    	<% 
    	}
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>