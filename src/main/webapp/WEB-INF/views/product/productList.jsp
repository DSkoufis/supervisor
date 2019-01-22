<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="visor" uri="http://www.supervisor.com/tags/core" %>

<jsp:useBean id="products" scope="request" type="java.util.List"/>
<jsp:useBean id="vendor" type="com.supervisor.domain.product.Vendor"/>
<jsp:useBean id="version" type="com.supervisor.domain.product.MajorVersion"/>

<html>
<head>
    <title>Products -- List</title>
</head>
<body>
<h1>Products List</h1>
<table class="table table-striped responsive">
    <thead>
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Vendors</th>
        <th>Versions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${products}" var="product">
        <tr>
            <td><c:out value="${product.id}"/></td>
            <td><c:out value="${product.name}"/></td>
            <td>
                <visor:joinList list="${product.vendors}" delimiter=", " item="vendor">
                    ${vendor.id}
                </visor:joinList>
            </td>
            <td>
                <visor:joinList list="${product.versions}" delimiter=", " item="version">
                    ${version.version}
                </visor:joinList>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
</body>
</html>