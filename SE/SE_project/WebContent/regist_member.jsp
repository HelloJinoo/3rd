<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>회원등록</h2>
	<form action="regist_member2.jsp" method="post">
	ID : <input type="text" name="id" > <br/>
	비밀번호 : <input type="password" name="pwd" ><br/>
	주소 : <input type="text" name="addr" ><br/>
	현황 : <input type="number" name="current" max="1" min="0"><br/>
	권한 : <input type="number" name="authority" max="2" min="1"><br/>
	<input type="submit" value="회원등록">
	</form>
	
</body>
</html>