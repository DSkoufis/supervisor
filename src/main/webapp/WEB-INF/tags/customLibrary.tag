<%@ tag description="Helper to import custom created libraries, either JS or CSS"
        import="com.supervisor.util.constant.ViewMapping, java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="fileName" required="true" type="java.lang.String"
              rtexprvalue="true" description="The file path. Must start with a forward slash" %>
<%@ attribute name="isCss" required="false" rtexprvalue="true"
              description="If the library is a CSS library. Can be either a string or a boolean (Defaults to false)" %>

<c:set var="jsPath" value="${ViewMapping.STATIC_JS_ROOT}"/>
<c:set var="cssPath" value="${ViewMapping.STATIC_CSS_ROOT}"/>

<c:choose>
    <c:when test="${isCss != null && Boolean.valueOf(isCss)}">
        <link rel="stylesheet" href="${cssPath}${fileName}">
    </c:when>
    <c:otherwise>
        <script src="${jsPath}${fileName}"></script>
    </c:otherwise>
</c:choose>
