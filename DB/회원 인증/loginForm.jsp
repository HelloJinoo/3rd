<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>로그인</title>
</head>
<body>
	<h2>로그인폼</h2>
	
	<form method="post" action="cookieLoginPro.jsp">
		아이디 : <input type="text" name="id" maxlength="12"><br/>
		비밀번호 : <input type="password" name="passwd" maxlength="12"><br/>
		<input type="submit" value="로그인">	
		<input type="submit" value="회원가입" onclick="location.href=insertMemberForm.jsp">	
	</form>
</body>
</html>