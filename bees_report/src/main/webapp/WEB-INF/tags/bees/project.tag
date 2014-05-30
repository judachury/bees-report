<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>

<%@ attribute name="project" type="org.beesden.report.model.Project" required="true" %>

<ul class="projectInfo">

	<%-- Project name --%>
	<li>
		<strong><fmt:message key="project.name" /></strong>
		<span><a href="/project/${project.name}" title="${project.name}">${project.name}</a></span>
	</li>
	
	<%-- Project due date --%>
	<li>
		<strong><fmt:message key="project.duedate" /></strong>
		<span><fmt:formatDate value="${project.dueDate}" pattern="EEE dd MMM yyyy" /></span>
	</li>
	
	<%-- Project name --%>
	<li>
		<strong><fmt:message key="project.testdate" /></strong>
		<span><fmt:formatDate value="${project.testDate}" pattern="EEE dd MMM yyyy" /></span>
	</li>
	
	<%-- Project name --%>
	<li>
		<strong><fmt:message key="project.developer" /></strong>
		<span>${project.leadDeveloper.firstname} ${project.leadDeveloper.surname}</span>
	</li>
	
	<%-- Project name --%>
	<li>
		<strong><fmt:message key="project.manager" /></strong>
		<span>${project.projectManager.firstname} ${project.projectManager.surname}</span>
	</li>
	
</ul>