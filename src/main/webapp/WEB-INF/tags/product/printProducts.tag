<%@ tag description="Prints a table with the given products list" body-content="empty" isELIgnored="false"
        pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="visor" uri="http://www.supervisor.com/tags/core" %>

<%@ attribute name="products" required="true" type="java.util.List<com.supervisor.domain.product.Product>"
              rtexprvalue="true" description="A list of products that is going to be printed as a table" %>

<jsp:useBean id="vendor" class="com.supervisor.domain.product.Vendor"/>
<jsp:useBean id="version" class="com.supervisor.domain.product.MajorVersion"/>


<c:forEach items="${products}" var="product" varStatus="loopInfo">
    <tr>
        <th scope="row"><c:out value="${loopInfo.count}"/></th>
        <td><c:out value="${product.name}"/></td>
        <td><visor:joinList list="${product.vendors}" delimiter=", " var="vendor">
            ${vendor.name}
        </visor:joinList></td>
        <td><visor:joinList list="${product.versions}" delimiter=", " var="version">
            ${version.version}
        </visor:joinList></td>
    </tr>
</c:forEach>