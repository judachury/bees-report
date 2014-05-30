<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<c:choose>
	<c:when test="${empty projectList}">
		<p class="message"><fmt:message key="project.list.empty" /></p>
		<a class="button advance action create" href="/project"><fmt:message key="project.create" /></a>
	</c:when>
	<c:otherwise>
		<bees:pagination pagination="${pagination}" sortKey="project" summary="true" />
		<c:forEach var="project" items="${projectList}">
			<bees:project project="${project}" />
		</c:forEach>
		<a class="button advance action create" href="/project"><fmt:message key="project.create" /></a>
		<bees:pagination pagination="${pagination}" sortKey="project" />
	</c:otherwise>
</c:choose>