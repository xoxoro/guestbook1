<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
 <%@ page import="java.util.List" %>
 <%@ page import="com.javaex.vo.GuestbookVo" %>   
 <%@ page import="com.javaex.dao.GuestbookDao" %>

<%
	//다오 메모리에 올리기	

 	GuestbookDao guestbookDao = new GuestbookDao();
 
 	List<GuestbookVo> guestbookList = guestbookDao.getList();
 
 	//테스트
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

	<!------ 등록폼영역 ------->
	<form action="./add.jsp" method="get">
	<table border="1" width="500px">
		<tr>
			<td>
			이름<input type="text" name="user" value="">
			</td><td>
			<td>
			비밀번호<input type="text" name="upw" value="">
			</td>
			<td></td>
		</tr>
		<tr>
			<td colspan="4">
				<textarea rows="5" cols="65" name="textarea"></textarea>
			</td>
		</tr>
		<tr>
			<td colspan="4">
				<button type= "submit">확인</button>
			</td>
		</tr>
	</table>
	</form>
	<!------ 등록폼영역 ------->
	<br>
	
	<!------ 리스트영역 ------->
	<%
	for(int i=0; i<guestbookList.size(); i++){
	%>
		<table border="1" width="500px">
			<tr>
				<td><%=guestbookList.get(i).getNo()%></td>
				<td><%=guestbookList.get(i).getName()%></td>
				<td><%=guestbookList.get(i).getRegDate()%></td>
				<td><a href="./deleteForm.jsp?id=<%=guestbookList.get(i).getNo()%>">삭제</a></td>
			</tr>
			<tr>
				<td colspan="4"><br>
					<%=guestbookList.get(i).getContent()%>
				</td>
			</tr>
		</table>
		<br>
	<%	
	}
	%>
	<!------ 리스트영역 ------->
	
	
</body>
</html>