<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<form action="/user" method="POST">	

	<input type="hidden" name="userId" value="${user.id}" />

	<jsp:include page="../forms/userCredentials.jsp" />
	<jsp:include page="../forms/userDetails.jsp" />
	
	<button class="button advance"><fmt:message key="forms.button.create.report" /></button>
	
</form>