<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<bees:project project="${report.project}" />

<h2>${report.name}</h2>

<dl class="items">
	<c:forEach items="${report.items}" var="item">
		<dt><span><progress value="${item.value}" max="100"></progress></span></dt>
		<dd><span>${item.name}</span></dd>
	</c:forEach>
	<dt class="comments"><fmt:message key="report.comments" /></dt>
	<dd class="comments">${report.comments}</dd>
</dl>

<a href="/report?copy=${report.id}" class="button advance action"><fmt:message key="report.clone" /></a>
<a href="/report/${report.name}/update" class="button advance action"><fmt:message key="report.update" /></a>