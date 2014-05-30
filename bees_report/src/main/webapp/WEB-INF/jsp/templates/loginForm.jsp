<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form class="login" method="POST" action="/login.do">
	<jsp:include page="../forms/login.jsp" />	
	<jsp:include page="../partials/errors.jsp" />		
	<button class="button advance"><fmt:message key="forms.button.login" /></button>		
</form>	