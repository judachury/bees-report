<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<%@ attribute name="project" type="org.beesden.report.model.Project" required="true" %>

<c:choose>
	<c:when test="${empty project.reports}">
		<p class="message"><fmt:message key="report.list.empty" /></p>
	</c:when>
	<c:otherwise>
		<h2><fmt:message key="project.reports" /></h2>
		<dl class="items">
			<c:forEach var="report" items="${project.reports}">	
				<dt><span><progress value="${report.avgStatus}" max="100"></progress></span></dt>			
				<dd><span><a href="/report/${report.name}">${report.name}</a></span></dd>
			</c:forEach>
		</dl>
	</c:otherwise>
</c:choose>