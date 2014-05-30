<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<%@ taglib prefix="util" uri="/WEB-INF/tags/beesden.tld" %>
<%@ taglib prefix="bees" tagdir="/WEB-INF/tags/bees" %>
<%@ taglib prefix="panel" tagdir="/WEB-INF/tags/panels" %>
<%@ page contentType="text/javascript" %>

<script>
	var _gaq = _gaq || [];
	_gaq.push(['t1._setAccount', 'UA-32335311-1']);
	_gaq.push(['t1._setDomainName', '<% String getURL=request.getServerName().toString();%><%=getURL%>']);
	_gaq.push(['t1._setAllowLinker', true]);
	_gaq.push(['t1._trackPageview']);
	<c:if test="${!empty config.analyticsCode}">
	_gaq.push(['t2._setAccount', '${config.analyticsCode}']);
	_gaq.push(['t2._trackPageview']);
	</c:if>
	(function() {
		var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
		ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
		var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	})();
</script>