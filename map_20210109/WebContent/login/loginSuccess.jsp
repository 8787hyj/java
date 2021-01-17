<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%	request.setCharacterEncoding("utf-8"); %>    
<!DOCTYPE html> 
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

	<div align=center>
		
		<%
		String sessionUserId=(String)session.getAttribute("sessionUserId");
		String userId = request.getParameter("user_id");
		out.println(" 사번: "+userId+" 님 로그인 성공 하였습니다."); 
		%>
		
		
		<%session.invalidate(); %>



	</div>
</body>
</html>