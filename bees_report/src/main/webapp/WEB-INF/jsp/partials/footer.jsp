<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<footer class="footer container">
	<section>
	
		<ul class="footer-left">
			<c:choose>
				<c:when test="${!empty config.footerLeft}">${config.footerLeft}</c:when>
				<c:otherwise>
					<c:if test="${currentUser.authority > 1}">
						<fmt:message key="global.title.projectlist" var="title" />
						<li><a href="/" title="title">${title}</a></li>
					</c:if>
					<fmt:message key="global.title.userlist" var="title" />
					<li><a href="/userlist" title="title">${title}</a></li>
				</c:otherwise>
			</c:choose>				
		</ul>
		
		<ul class="footer-right">
			<c:choose>
				<c:when test="${!empty config.footerRight}">${config.footerRight}</c:when>
				<c:otherwise>
					<fmt:message key="global.title.logout" var="title" />
					<li><a href="/logout" title="title">${title}</a></li>
					<li><fmt:message key="global.version" /></li>
				</c:otherwise>
			</c:choose>				
		</ul>
		
		<ul class="footer-center">
			<li><fmt:message key="global.copyright" /></li>
		</ul>
	
	</section>
</footer>