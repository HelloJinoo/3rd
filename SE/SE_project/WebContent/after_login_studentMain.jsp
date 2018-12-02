
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="Main.css">
<script src="function.js"></script>
<title>Insert title here</title>
</head>
<body>
	
		<form action="show_myinformation.jsp" method ="post">
			<input type="submit" value="개인정보확인" name="id" >
		</form>
		</br>
		<form action="show_allsubject.jsp" method ="post">
			<input type="submit" value="수강편람조회" >
		</form>
		<form action="apply_subject.jsp" method ="post">
			<input type="submit" value="수강신청" >
		</form>
		<form action="view_timetable.jsp" method ="post">
			<input type="submit" value="시간표확인" >
		</form>
		<form action="check_grade.jsp" method ="post">
			<input type="submit" value="성적조회" >
		</form>
		<form action="check_graduate.jsp" method ="post">
			<input type="submit" value="졸업자가진단" >
		</form>
		<form action="check_scholarship.jsp" method ="post">
			<input type="submit" value="장학여부확인" >
		</form>
			<input type="button" value ="로그아웃" onclick="logout()">
	
</body>
</html>