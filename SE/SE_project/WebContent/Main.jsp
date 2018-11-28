<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="Main.css">
<title>Main</title>
</head>
<body>
	<div id="main_view">
		<h2>통합정보시스템</h2>

		<form  action="login.jsp" method="post">
		   학번 : <input type="text" name ="stdno" >
		  </br></br>
		  비밀번호 : <input type="password" name="pwd" >
		  <br>
		  <input type="submit" value="로그인">
		</form> 
	</div>
	
</body>
</html>