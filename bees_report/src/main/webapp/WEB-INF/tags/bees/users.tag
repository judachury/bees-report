<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<%@ attribute name="items" type="java.util.ArrayList" required="true" %>
<%@ attribute name="selected" type="java.lang.Integer" required="false" %>

<option value=""><fmt:message key="forms.user.placeholder" /></option>
<c:forEach items="${items}" var="user">
	<c:set var="sel" value="${selected == user.id ? 'selected' : ''}" />
	<option ${sel} value="${user.id}">${user.firstname} ${user.surname}
</c:forEach>