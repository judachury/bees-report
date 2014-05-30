<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<ul class="breadcrumbs">
	<fmt:message key="global.title.dashboard" var="title" />
	<li><a href="/" title="${title}">Home</a></li>
	
	<c:set var="projectLink" value="${empty project ? empty report ?  '' : report.project : project}" />	
	<c:if test="${!empty projectLink}">		
		<li><a href="/project/${projectLink.name}" title="${projectLink.name}">${projectLink.name}</a></li>
	</c:if>	
	
	<c:set var="reportLink" value="${empty report || empty report.id ? '' : report}" />	
	<c:if test="${!empty reportLink}">		
		<li><a href="/report/${reportLink.name}" title="${reportLink.name}">${reportLink.name}</a></li>
	</c:if>	
	
	<c:if test="${section == 'user' && layout != 'userList'}">
		<fmt:message key="global.title.userlist" var="title" />
		<li><a href="/userlist" title="${title}">${title}</a></li>
	</c:if>
	
	<fmt:message key="global.title.${fn:toLowerCase(layout)}" var="title" />
	<li>${title}</li>	
	
</ul>