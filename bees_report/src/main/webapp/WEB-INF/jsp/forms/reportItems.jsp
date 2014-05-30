<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<dl>
	<c:forEach varStatus="i" var="item" items="${report.items}">
		<dt>
			<bees:input name="itemValue-${i.index}" type="range" range="0,100,10" label="forms.itemvalue" value="${item.value}" />
		</dt>			
		<dd><bees:input name="itemName-${i.index}" type="text" label="forms.itemname" value="${item.name}" /></dd>
		<c:set var="indexValue" value="${i.index + 1}" />
	</c:forEach>
	
	<c:forEach var="item" begin="${indexValue}" end="${indexValue + param.fields}">
		<c:if test="${item != indexValue}">
			<dt>
				<bees:input name="itemValue-${item}" type="range" range="0,100,10" label="forms.itemvalue" value="0" />
			</dt>			
			<dd><bees:input name="itemName-${item}" type="text" label="forms.itemname" /></dd>
		</c:if>
	</c:forEach>
</dl>

<c:url var="urlString" value="${requestScope['javax.servlet.forward.request_uri']}">
	<c:forEach var="urlParam" items="${param}">
		<c:if test="${urlParam.key != 'fields'}">
			<c:param name="${urlParam.key}" value="${urlParam.value}" />
		</c:if>
	</c:forEach>
	<c:param name="fields" value="${param.fields + 1}" />
</c:url>

<fmt:message key="report.add.field" var="title" />
<a class="addField" href="${urlString}" title="${title}">${title}</a>
