<%@ tag description="Helper to import static libraries" import="com.supervisor.util.constant.ViewMapping, java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="file" required="true" type="java.lang.String"
              rtexprvalue="true" description="The static library path. Path must start with a forward slash" %>
<%@ attribute name="isCss" required="false" rtexprvalue="true"
              description="If the library is a CSS library. Can be either a string or a boolean (Defaults to false)" %>

<c:set var="jsPath" value="${ViewMapping.STATIC_JS_ROOT}"/>
<c:set var="cssPath" value="${ViewMapping.STATIC_CSS_ROOT}"/>

<c:choose>
    <c:when test="${Boolean.valueOf(isCss)}">
        <link rel="stylesheet" href="${cssPath}${file}">
    </c:when>
    <c:otherwise>
        <script src="${jsPath}${file}"></script>
    </c:otherwise>
</c:choose>
