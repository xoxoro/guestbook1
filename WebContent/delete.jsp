<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>

	<!-- 삭제눌렀을때(실제삭제)리다이렉트필요 -->
	<%	request.setCharacterEncoding("UTF-8");//post쓸때 한글안깨지도록 하는 메소드
	
	GuestbookDao guestbookDao = new GuestbookDao();
	int no = Integer.parseInt(request.getParameter("no"));
	String password = request.getParameter("password");	
	
	GuestbookVo vo = new GuestbookVo();
	vo.setNo(no);
	vo.setPassword(password);
	
	int count = guestbookDao.delete(vo);
	if(count > 0) {
		System.out.println(count + "건이 삭제되었습니다.");
	}
	
	response.sendRedirect("./addList.jsp");
	%>