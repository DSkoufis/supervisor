<%@ tag description="Helper to import static libraries" import="com.supervisor.util.constant.ViewMapping, java.lang.Boolean" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute name="library" required="true" type="java.lang.String"
              rtexprvalue="true" description="The static library path. Path must start with a forward slash" %>
<%@ attribute name="isCss" required="false" rtexprvalue="true"
              description="If the library is a CSS library. Can be either a string or a boolean (Defaults to false)" %>

<c:set var="librariesPath" value="${ViewMapping.STATIC_LIBRARIES_ROOT}"/>
<c:set var="libraryPath" value="${librariesPath}${library}"/>

<c:choose>
    <c:when test="${isCss != null && Boolean.valueOf(isCss)}">
        <link rel="stylesheet" href="${libraryPath}">
    </c:when>
    <c:otherwise>
        <script src="${libraryPath}"></script>
    </c:otherwise>
</c:choose>
