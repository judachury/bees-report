<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<form class="login" method="POST" action="/password">	
	<jsp:include page="../forms/password.jsp" />	
	<jsp:include page="../partials/errors.jsp" />		
	<button class="button advance"><fmt:message key="forms.button.password" /></button>		
</form>	