<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>Insert title here</title>
</head>
<body>

	<h2>강의등록</h2>
	<form action="regist_subject2.jsp">
	강의명 : <input type="text" name="sub_name" required>
	교수명 : <input type="text" name="pro_name" required >
	학년 : <input type="number" name="grade" required>
	인원 : <input type="number" name="personnel" required>
	시간 : <input type="text" name="time" required>
	요일 : <input type="text" name="day" required>
	장소 : <input type="text" name="place" required>
	<input type="submit" value="강의등록" required>
	</form>
	
	
	
	
</body>
</html>