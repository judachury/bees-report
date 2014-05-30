<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<c:set var="seoTitle">
	<c:choose>
		<c:when test="${!empty content.heading}">${content.heading} ${config.seoTitle}</c:when>
		<c:otherwise><fmt:message key="global.title.${fn:toLowerCase(layout)}" /> ${config.seoTitle}</c:otherwise>
	</c:choose>
</c:set>

<c:set var="seoDescription">
	<c:choose>
		<c:when test="${!empty config.seoDescription}">${config.seoDescription}</c:when>
	</c:choose>
</c:set>

<title>${seoTitle}</title>
<meta name="description" content="${seoDescription}" />
<meta name="keywords" content="${config.seoKeywords}" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"> 