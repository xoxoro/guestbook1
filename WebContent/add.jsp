<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %> 
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>  
<!-- <!-- 등록폼(리다이렉트필요) -->
<%
	//guestboodDao의 insert 쿼리를 쓸거니까 메모리를 올림
	GuestbookDao guestbookDao = new GuestbookDao();	
	
	//파라미터값 가져오기
	String name = request.getParameter("name");
	String password = request.getParameter("password");
	String content = request.getParameter("textarea");
	
	//guestbookVo에 넣어줘야함
	GuestbookVo guestbookVo = new GuestbookVo(name,password,content);

	//저장
	guestbookDao.insert(guestbookVo);
		
	//리다이렉션
	response.sendRedirect("./addList.jsp");

%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
</body>
</html>