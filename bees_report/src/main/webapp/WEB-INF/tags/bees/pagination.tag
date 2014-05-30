<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<%@ attribute name="pagination" type="java.util.HashMap" required="true" %>
<%@ attribute name="sortKey" required="false" %>
<%@ attribute name="summary" type="java.lang.Boolean" required="false" %>

<div class="paginationContainer">

	<c:if test="${pagination.pages > 1}">
	
		<c:set var="x" value="${!empty config.paginationSize ? config.paginationSize : 2}" />
		<c:set var="first" value="${(pagination.pages - pagination.page) > x ? pagination.page - x : pagination.pages - (2 * x)}" />	
		<c:set var="first" value="${first < 1 ? 1 : first}" />	
		<c:set var="last" value="${pagination.page <= x ? (2 * x + 2) - first : pagination.page + x}" />
		<c:set var="last" value="${last > pagination.pages ? pagination.pages : last}" />	
	
		<c:set var="pageUrl" value="${url.uri}?" />
		<c:if test="${!empty param.keywords}">
			<c:set var="pageUrl" value="${pageUrl}keywords=${param.keywords}&amp;" />
		</c:if>
		<c:if test="${!empty param.sort}">
			<c:set var="pageUrl" value="${pageUrl}sort=${param.sort}&amp;" />
		</c:if>
		<c:if test="${!empty param.results}">
			<c:set var="pageUrl" value="${pageUrl}results=${param.results}&amp;" />
		</c:if>
	
		<ol class="pagination">
			<li>
				<fmt:message key="global.pagination.prev" var="title" />
				<c:choose>
					<c:when test="${pagination.page <= 1}"><em>${title}</em></c:when>
					<c:otherwise><a href="${pageUrl}page=${pagination.page - 1}" title="${title}">${title}</a></c:otherwise>		
				</c:choose>
			</li>
			
			<c:forEach varStatus="page" begin="${first}" end="${last}">
				<li>
					<c:choose>
						<c:when test="${pagination.page == page.index}"><span>${page.index}</span></c:when>
						<c:otherwise><a href="${pageUrl}page=${page.index}">${page.index}</a></c:otherwise>
					</c:choose>
				</li>
			</c:forEach>
			<li>
				<fmt:message key="global.pagination.next" var="title" />
				<c:choose>
					<c:when test="${pagination.page == pagination.pages}"><em>${title}</em></c:when>
					<c:otherwise><a href="${pageUrl}page=${pagination.page + 1}" title="${title}">${title}</a></c:otherwise>		
				</c:choose>
			</li>
		</ol>
	
	</c:if>

	<c:if test="${!empty sortKey && pagination.size > 1}">
		<form class="sortOrder" action="${requestScope['javax.servlet.forward.request_uri']}?" method="GET">
			<c:forEach var="sortParam" items="${param}">
				<c:if test="${sortParam.key != 'sort' && sortParam.key != 'ajax'}">
					<input type="hidden" name="${sortParam.key}" value="${sortParam.value}" />
				</c:if>
			</c:forEach>
			
			<fieldset>
				<legend><fmt:message key="forms.legend.sort" /></legend>
				<bees:input name="sort" type="select">
					<option value=""><fmt:message key="forms.sort.placeholder" /></option>			
					<c:forEach var="sort" items="${config.sortOrders[sortKey]}">						
						<option ${param.sort == sort.value ? 'selected="selected" ' : ''}value="${sort.value}">${sort.key}</option>
					</c:forEach>
				</bees:input>
			</fieldset>	 
			<button class="button return jsHide">&raquo;</button>
		</form>
	</c:if>

	<c:if test="${summary}">
		<span class="summary">
			<fmt:message key="${sortKey}.pagination.${pagination.size > 0 ? 'results' : 'empty'}${pagination.pages > 1 ? '.paginate' : ''}">
				<fmt:param value="${pagination.page}" />
				<fmt:param value="${pagination.pages}" />
				<fmt:param value="${pagination.first + 1}" />
				<fmt:param value="${pagination.last}" />
				<fmt:param value="${pagination.size}" />
			</fmt:message>
		</span>
	</c:if>
	
</div>
