<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<tiles:importAttribute name="section" scope="request" />

<!doctype html>
<html lang="en">

<head> 
	<link rel="stylesheet" type="text/css" href="/assets/styles/styles.css" />
	<link rel="shortcut icon" href="/assets/favicon.ico">
	<script src="/assets/scripts/scripts.js"></script>	
	<jsp:include page="./partials/meta.jsp" />	
</head>

<body>

<div id="main" class="wrapper">

	<jsp:include page="./partials/header.jsp" />

	<article class="article container">
	
		<section id="info" class="content ${section}">
		
			<jsp:include page="./partials/breadcrumbs.jsp" />
			<jsp:include page="./partials/errors.jsp" />
			
			<h1><fmt:message key="global.title.${fn:toLowerCase(layout)}" /></h1>
			<tiles:insertAttribute name="content" />
			
		</section>	
	</article>	

	<jsp:include page="./partials/footer.jsp" />

</div>

</body>
</html>