<%@ tag description="Core Page Template" import="com.supervisor.utils.constants.ViewMapping" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="header" fragment="true" required="false" %>
<%@ attribute name="jsImports" fragment="true" required="false" %>

<c:set var="libraries" value="${ViewMapping.STATIC_LIBRARIES_ROOT}" />

<!DOCTYPE html>

<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">

    <link rel="stylesheet" href="${libraries}/bootstrap-4.2.1/bootstrap.min.css">

    <jsp:invoke fragment="header"/>
</head>
<body>

<jsp:doBody/>

<script src="${libraries}/jquery-3.3.1/jquery-3.3.1.min.js"></script>
<script src="${libraries}/popper.js-1.0/popper.min.js"></script>
<script src="${libraries}/bootstrap-4.2.1/bootstrap.min.js"></script>
<jsp:invoke fragment="jsImports"/>
</body>
</html>
