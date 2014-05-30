<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>
	
<fieldset>
	<legend><fmt:message key="forms.legend.report" /></legend>
	<bees:input type="week" name="name" value="${report.name}" />
</fieldset>