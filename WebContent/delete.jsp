<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>

<!-- 삭제눌렀을때(실제삭제)리다이렉트필요 -->
<%	request.setCharacterEncoding("UTF-8");//post쓸때 한글안깨지도록 하는 메소드

	//가져와서 삭제할 파라미터값은 코드랑 비밀번호
	String no = request.getParameter("no");
	int i = Integer.parseInt(no);
	String password = request.getParameter("password");
	
	//(1) 생성자 만들어서 올리거나
	//GuestbookVo guestbookVo = new GuestbookVo(no, password);
	
/* 	//(2)기본 생성자를 이용하고 set세터를 사용해서 값 초기화
 	GuestbookVo guestbookVo = new GuestbookVo();
	guestbookVo.setNo(no);
	guestbookVo.setPassword(password);*/
	
	//guestboodDao의 delete 쿼리를 쓸거니까 메모리를 올림
	GuestbookDao guestbookDao = new GuestbookDao();
	List<GuestbookVo> guestbookList = guestbookDao.getList();
	
	//db에서 가져온 비밀번호가 deleteForm에서 가져온 비밀번호
	if(password == guestbookList.get(i).getPassword()){ 
		guestbookDao.delete(i);
		//response.sendRedirect("./addList.jsp");
	}else{ //아닐 경우 바로 리스트로 이동한다
		response.sendRedirect("./addList.jsp");
	}

%>
