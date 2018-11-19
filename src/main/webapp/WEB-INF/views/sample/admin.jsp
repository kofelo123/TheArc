<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<h1>/sample/admin page</h1>


<p>principal : <sec:authentication property="principal"/></p>
<p>MemberVO : <sec:authentication property="principal.member"/></p>
<p>사용자이름 : <sec:authentication property="principal.member.uname"/></p>
<p>사용자아이디 : <sec:authentication property="principal.member.uid"/></p>
<p>사용자 권한 리스트  : <sec:authentication property="principal.member.authList" /></p>
<p>사용자 권한 리스트의 첫번쨰  : <sec:authentication property="principal.member.authList[0]" /></p>
<p>사용자 권한 리스트의 첫번째 AuthVO의 authority  : <sec:authentication property="principal.member.authList[0].authority" /></p>



<a href="/customLogout">Logout</a>


</body>
</html>
