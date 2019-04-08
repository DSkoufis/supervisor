<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="visor" uri="http://www.supervisor.com/tags/core" %>

<tr>
    <c:choose>
        <c:when test="${param.isHeader}">
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Vendors</th>
            <th scope="col">Versions</th>
        </c:when>
        <c:otherwise>
            <td><c:out value="${requestScope.loopInfo.count}"/></td>
            <td><c:out value="${requestScope.product.name}"/></td>
            <td>
                <visor:joinList list="${requestScope.product.vendors}" delimiter=", " var="vendor">
                    ${vendor.name}
                </visor:joinList>
            </td>
            <td>
                <visor:joinList list="${requestScope.product.versions}" delimiter=", " var="version">
                    ${version.version}
                </visor:joinList>
            </td>
        </c:otherwise>
    </c:choose>
</tr>