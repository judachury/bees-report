<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%-- Error messages --%>
<c:if test="${!empty errors}">
	<ul class="errors">
		<c:forEach var="error" items="${errors}">
			<li><fmt:message key="${error}" /></li>
		</c:forEach>
	</ul>
</c:if>