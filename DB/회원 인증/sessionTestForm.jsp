<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>>정보입력 폼</title>
</head>
<body>
	<h2>정보입력 폼</h2>
	
	<form method="post" action="sessionTestPro.jsp">
      아이디 : <input type="text" name = "id" maxlength = "12"><br/>
      패스워드 : <input type="password" name = "passwd" maxlength="12"><br/>
      <input type = "submit" value="정보입력">
	</form>
</body>
</html>