<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.javaex.vo.GuestVo" %>    
<%
	List<GuestVo> updatelist = (List<GuestVo>)request.getAttribute("guestVo");
%>        
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form action = "/guestbook2/gtr" method="get"> 
	이름 : <input type="text" name = "name"  value = "<%=updatelist.get(0).getName()%>"> <br>
	내용 : <input type="text" name = "content" value = "<%=updatelist.get(0).getContent() %>"> <br>
	비밀번호 : <input type="text" name = "pwd" value = ""> <br>
	<input type="hidden" name = "no" value = "<%=updatelist.get(0).getGuestno()%>">
	<input type="hidden" name = "date" value = "<%=updatelist.get(0).getDate()%>">
	<input type="hidden" name = "action" value="update">
	<button type = "submit">변경</button>	
	</form>
</body>
</html>