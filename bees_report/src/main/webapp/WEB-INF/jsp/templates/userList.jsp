<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<bees:pagination pagination="${pagination}" sortKey="user" summary="true" />
<dl>
	<c:forEach items="${userList}" var="user">
		<dt><a href="/user/${user.name}">${user.firstname} ${user.surname}</a></dt>
		<dd>${user.email}</dd>
	</c:forEach>
</dl>

<a href="/user" class="button advance action"><fmt:message key="user.add" /></a>

<bees:pagination pagination="${pagination}" sortKey="user" summary="true" />