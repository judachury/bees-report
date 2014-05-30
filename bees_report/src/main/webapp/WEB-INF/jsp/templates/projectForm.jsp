<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<form action="/project" method="POST">	

	<bees:input type="hidden" name="projectId" value="${project.id}" />
	<jsp:include page="../forms/project.jsp" />

	<button class="button advance"><fmt:message key="forms.button.create.project" /></button>
	
</form>