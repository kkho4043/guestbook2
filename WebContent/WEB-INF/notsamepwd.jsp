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
	
	비밀번호가 틀렸습니다.<br>
	<a href= "/guestbook2/gtr?action=<%=where%>form&no=<%=no%>">다시입력하기</a>
	
	<a href= "/guestbook2/gtr?action=list">리스트로가기</a>
</body>
</html>