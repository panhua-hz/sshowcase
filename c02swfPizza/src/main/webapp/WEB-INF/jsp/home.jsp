<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ page session="false"%>
<html>
<head>
<title>Home</title>
</head>
<body>
	<h1>
		<s:message code="app.welcome" />
	</h1>

	<a href="<c:url value="/pizza1" />">Pizza1</a>
	<br>
	<a href="<c:url value="/pizza2" />">Pizza2</a>
	<br>
	
</body>
</html>