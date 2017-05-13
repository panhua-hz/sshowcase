<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
</head>
<body>
	<h1>
		<s:message code="app.welcome" />
	</h1>
	<div>
		<ol>
		<c:forEach items="${linkVOList}" var="linkVO">
			<li><a href="<c:url value="${linkVO.linkUrl}" />"><c:out value="${linkVO.linkName}" /></a></li>
			<br>
		</c:forEach>
		</ol>
	</div>	
</body>
</html>