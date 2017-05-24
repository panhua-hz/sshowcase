<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
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
	<sf:form method="POST" modelAttribute="noteForm" acceptCharset="UTF-8">
		<sf:hidden path="username" />
		<sf:checkboxes items="${entTagList}" path="selTags" itemLabel="tagName" itemValue="id"/>
		<br>
		<sf:label path="addTags">More Tags</sf:label>
		<sf:input path="addTags"/>
		<br>
		<sf:textarea path="message"/>
		<br>
		<input type="submit" value="Submit Note" />
	</sf:form>
	</div>
	<div>
		<h3>Note List</h3>
		<dl>
			<c:forEach items="${noteShowList}" var="note">
				<dt>
					<c:forEach items="${note.tags}" var="tag">
						<c:out value="${tag}"></c:out>&nbsp;|
					</c:forEach>
				</dt>
				<dd>
					<c:out value="${note.noteID}" />&nbsp;[
					<c:out value="${note.latitude}" />;&nbsp;
					<c:out value="${note.longitude}" />]
				</dd>
				<dd>
					<c:out value="${note.createAt}" />
				</dd>
				<dd>
					<c:out value="${note.message}" />
				</dd>

			</c:forEach>
		</dl>
	</div>		
</body>
</html>
