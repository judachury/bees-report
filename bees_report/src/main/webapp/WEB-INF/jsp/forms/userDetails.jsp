<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<fieldset>
	<legend><fmt:message key="forms.legend.userdetails" /></legend>
	
	<%-- User information --%>
	<bees:input name="firstname" required="true" value="${user.firstname}" />
	<bees:input name="surname" required="true" value="${user.surname}" />
	<bees:input name="email" required="true" type="email" value="${user.email}" />	
	
	<%-- Authorities --%>
</fieldset>