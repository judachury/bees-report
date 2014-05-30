<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="section" scope="request" />
		
<jsp:include page="./partials/breadcrumbs.jsp" />
<jsp:include page="./partials/errors.jsp" />

<h1><fmt:message key="global.title.${fn:toLowerCase(layout)}" /></h1>
<tiles:insertAttribute name="content" />