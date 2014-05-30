<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<bees:project project="${project}" />
<bees:reports project="${project}" />
	
<a class="button advance action" href="/report?project=${project.id}"><fmt:message key="report.create" /></a>
<a class="button advance action" href="/project/${project.name}/update"><fmt:message key="project.update" /></a>