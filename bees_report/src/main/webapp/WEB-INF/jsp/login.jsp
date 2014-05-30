<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<!doctype html>
<html lang="en">

<head> 
	<link rel="stylesheet" type="text/css" href="/assets/styles/styles.css" />
	<link rel="shortcut icon" href="/assets/favicon.ico">
	<script src="/assets/scripts/scripts.js"></script>	
	<jsp:include page="./partials/meta.jsp" />	
</head>

<body>
	<tiles:insertAttribute name="content" />
</body>