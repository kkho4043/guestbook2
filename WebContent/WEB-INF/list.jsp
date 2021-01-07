<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ page import = "java.util.List" %>
<%@ page import="com.javaex.vo.GuestVo" %>
<%
	List<GuestVo> guestList = (List<GuestVo>)request.getAttribute("glist");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>guest book</h1>
	<form action = "/guestbook2/gtr" method = "get">
		<table border = "1" width = 500>
			<tr>
				<td>이름</td>
				<td><input type ="text" name = "name"></td>
				<td>비밀번호</td>
				<td><input type ="text" name = "pwd"></td>
			</tr>
			<tr>
				<td colspan="4">
					<textarea rows=10 cols=65 name = "content">
					</textarea>
 				</td>
			</tr>
			<tr>
				<input type ="hidden" name = "action" value="add">
				<td colspan="4"><button>확인</button></td>
			</tr>
		</table>
	</form>

	<%
		for(int i = 0; i < guestList.size();i++) {
			GuestVo vo = guestList.get(i);
	%>
	<br>
	<table border = 1 width = 500>
		<tr>
			<td><%=vo.getGuestno()%> </td>
			<td><%=vo.getName()%></td>
			<td><%=vo.getDate()%></td>
			<td><a href= "/guestbook2/gtr?no=<%=vo.getGuestno()%>&action=upform">수정</a></td>
			<td><a href= "/guestbook2/gtr?no=<%=vo.getGuestno()%>&action=deform">삭제</a></td>
			
		</tr>
		<tr>
			<td colspan="5"><%=vo.getContent()%></td>
		</tr>
	</table>
	<%}%>
	
</body>
</html>

