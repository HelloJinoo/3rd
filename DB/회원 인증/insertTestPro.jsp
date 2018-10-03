<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="java.sql.*" %>
<% request.setCharacterEncoding("euc-kr"); %>

<%
   String id = request.getParameter("id");
   String passwd = request.getParameter("password");
   String name = request.getParameter("name");
   Timestamp register = new Timestamp(System.currentTimeMillis());
   
   Connection conn = null;
   PreparedStatement pstmt = null;
   String str = "";
   
   try{
      String jdbcUrl = "jdbc:mysql://localhost:3306/member";
      String dbId = "root";
      String dbPass = "asdasd1";
      
      Class.forName("com.mysql.jdbc.Driver");
      conn = DriverManager.getConnection(jdbcUrl,dbId,dbPass);
      
      String sql = "insert into member values(?,?,?,?)";
      pstmt = conn.prepareStatement(sql);
      pstmt.setString(1,id);
      pstmt.setString(2,passwd);
      pstmt.setString(3,name);
      pstmt.setTimestamp(4,register);
      pstmt.executeUpdate();
      
      str = "member ���̺� ���ο� ���ڵ带 �߰��߽��ϴ�.";
   }catch(Exception e) {
      e.printStackTrace();
      str = "member ���̺� ���ο� ���ڵ带 �߰��� �����߽��ϴ�.";
   }finally {
      if(pstmt != null) try{pstmt.close();}catch(SQLException sqle){}
      if(conn != null) try{conn.close();}catch(SQLException sqle){}
   }
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="EUC-KR">
<title>���ڵ� �߰�</title>
</head>
<body>
   <%=str %>
</body>
</html>