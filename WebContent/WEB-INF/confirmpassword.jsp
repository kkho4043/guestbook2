<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int no = (int)request.getAttribute("no");
	String where = (String)request.getAttribute("where");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "/guestbook2/gtr" method="get" >
			비밀번호: <input type="text" name = "pwd">
			<input type="hidden" name = "no" value="<%=no%>">
			<input type="hidden" name = "where" value="<%=where%>">
			<input type="hidden" name = "action" value="delandup">
			<button type = "submit">확인</button>	
	</form>
	<a href= "/guestbook2/gtr?action=list">리스트로가기</a>
</body>
</html>