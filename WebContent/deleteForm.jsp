<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String no = request.getParameter("no");//no name password content에 request넣어주기
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<!-- 삭제폼(비밀번호입력화면) -->
	<form action="./delete.jsp" method="get"> <!--주소값에 비번이 나타나지않도록 post/post로 받을때는 비번에 한글입력하면 깨짐-->
		<table>
			<tr>
				<td>비밀번호</td>
				<td><input type="password" name="pw"></td>
				<td><input type="submit" value="삭제"></td>
			</tr>
			<tr>
				<td colspan="3"><a href="./addList.jsp">메인으로 돌아가기</a></td>
			</tr>
		</table>
		<!-- id랑 비밀번호가 list랑 일치하게 가져와야함 -->
		<input type = 'text' name="" value="<%=no%>">
	</form>
</body>
</html>