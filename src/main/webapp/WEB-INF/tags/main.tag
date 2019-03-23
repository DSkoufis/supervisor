<%@ tag description="Core Page Template" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" tagdir="/WEB-INF/tags" %>

<%@ attribute name="header" fragment="true" required="false" %>
<%@ attribute name="footer" fragment="true" required="false" %>
<%@ attribute name="customImports" fragment="true" required="false" %>

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <custom:staticLibrary library="/bootstrap-4.2.1/bootstrap.min.css" isCss="${true}"/>

    <jsp:invoke fragment="header"/>
</head>
<body>
<script>
    SUP = null;
</script>

<custom:navbar/>

<div class="container pt-2">
    <jsp:doBody/>

    <jsp:invoke fragment="footer"/>
</div>

<custom:staticLibrary library="/jquery-3.3.1/jquery-3.3.1.min.js"/>
<custom:staticLibrary library="/popper.js-1.0/popper.min.js"/>
<custom:staticLibrary library="/bootstrap-4.2.1/bootstrap.min.js"/>

<jsp:invoke fragment="customImports"/>
</body>
</html>
