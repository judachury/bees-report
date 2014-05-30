<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setBundle basename="messages" scope="request" />

<!DOCTYPE html>

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
		<section>
			<h1><fmt:message key="error.title" /></h1>
			<div class="message">
				<p><fmt:message key="error.summary" /></p>
				<p><fmt:message key="error.link" /></p>
			</div>
		</section>	
	</article>	

	<jsp:include page="./partials/footer.jsp" />

</div>

</body>
</html>