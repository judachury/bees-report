<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<fieldset>
	<legend><fmt:message key="forms.legend.login" /></legend>
	<bees:input name="j_username" label="forms.username" />
	<bees:input name="j_password" type="password" label="forms.password" />
	<div class="formElement link"><a href="/password"><fmt:message key="user.password.reminder" /></a></div>
</fieldset>