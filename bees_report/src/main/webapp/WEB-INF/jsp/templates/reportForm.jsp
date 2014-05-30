<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<bees:project project="${report.project}" />

<form action="/report" method="POST">	

	<input type="hidden" name="projectId" value="${report.project.id}" />
	<input type="hidden" name="reportId" value="${empty param.copy ? report.id : ''}" />

	<jsp:include page="../forms/report.jsp" />

	<div class="items">
		<jsp:include page="../forms/reportItems.jsp" />
		<bees:input cssClass="comments" name="comments" type="textarea" value="${empty param.comments ? report.comments : param.comments}" />
	</div>

	<button class="button advance"><fmt:message key="forms.button.create.report" /></button>
	
</form>