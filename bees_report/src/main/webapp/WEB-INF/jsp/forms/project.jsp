<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<fieldset>

	<legend><fmt:message key="forms.legend.project" /></legend>
	
	<%-- Project info --%>
	<bees:input name="name" type="text" placeholder="true" value="${project.name}" />
	<bees:input name="summary" type="textarea" placeholder="true" value="${project.summary}" />	
	
	<%-- Project timeline --%>
	<c:if test="${!empty project}">
		<fmt:formatDate pattern="yyyy-MM-dd" value="${project.dueDate}" var="projectDue" />
		<fmt:formatDate pattern="yyyy-MM-dd" value="${project.testDate}" var="projectTest" />
	</c:if>
	<bees:input name="dueDate" type="date" value="${projectDue}" />
	<bees:input name="testDate" type="date" value="${projectTest}" />
	
	<%-- Get users --%>
	<bees:input name="manager" type="select">		
		<bees:users items="${userList}" selected="${empty param.manager ? project.projectManager.id : param.manager}" />
	</bees:input>
	<bees:input name="developer" type="select">
		<bees:users items="${userList}" selected="${empty param.developer ? project.leadDeveloper.id : param.developer}" />
	</bees:input>
	
</fieldset>