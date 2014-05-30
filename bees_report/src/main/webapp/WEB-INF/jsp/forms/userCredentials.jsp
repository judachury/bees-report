<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<fieldset>

	<legend><fmt:message key="forms.legend.user" /></legend>
	
	<%-- Logon credentials --%>
	<bees:input name="username" required="true" value="${user.name}" />
	<bees:input name="userStatus" type="select">
		<c:forEach var="status" begin="1" end="3">
			<c:set var="sel" value="${user.status == status ? 'selected' : ''}" />
			<option ${sel} value="${status}"><fmt:message key="user.status.${status}" /></option>
		</c:forEach>
	</bees:input>
	<bees:input name="userAuth" type="select">
		<c:forEach var="auth" begin="1" end="3">
			<c:set var="sel" value="${user.authority == auth ? 'selected' : ''}" />
			<option ${sel} value="${auth}"><fmt:message key="user.authority.${auth}" /></option>
		</c:forEach>
	</bees:input>
	<bees:input name="password" required="true" minLength="6" type="password" />
	<bees:input name="password2" required="true" equalTo="password"  type="password" />
	
</fieldset>