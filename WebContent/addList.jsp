<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!-- 임포트 자동이 아니라서 내가 적어줘야함 -->
<%@ page import="java.util.List" %>  
<%@ page import="com.javaex.dao.GuestbookDao" %>
<%@ page import="com.javaex.vo.GuestbookVo" %>  

<%
	//다오 메모리에 올리기	
	GuestbookDao guestbookDao = new GuestbookDao();
	
	//guestbookList 가져오기
	List<GuestbookVo> guestbookList = guestbookDao.getList();
	
	System.out.println(guestbookList.toString());
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 메인write폼 -->
	<!-- 등록폼영역 -->
	<table border="1" width="500px">
		<tr>
			<td>이름</td>
			<td><input type="text" name="" value=""></td>
			<td>비밀번호</td>
			<td><input type="password" name="" value=""></td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea rows="5" cols="65"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<button>글작성</button>
			</td>
		</tr>
	</table>
	<!-- 등록폼영역 -->
	
	<!-- 리스트영역 -->
	<%
	for(int i=0; i<guestbookList.size(); i++){%>
		<table border="1" width="500px">
			<tr>
				<td><%=guestbookList.get(i).getNo()%></td>
				<td><%=guestbookList.get(i).getName()%></td>
				<td><%=guestbookList.get(i).getRegDate()%></td>
				<td>삭제</td>
			</tr>
			<tr>
				<td colspan="4">
					<%=guestbookList.get(i).getContent()%><br>
					
				</td>
			</tr>
		</table>
		<br>
	<%	
	}
	%>
	<!-- 리스트영역 -->
	
	
</body>
</html>