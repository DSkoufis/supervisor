<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="visor" uri="http://www.supervisor.com/tags/core" %>

<jsp:useBean id="products" scope="request" type="java.util.List"/>
<jsp:useBean id="vendor" class="com.supervisor.domain.product.Vendor"/>
<jsp:useBean id="version" class="com.supervisor.domain.product.MajorVersion"/>

<html>
<head>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/css/bootstrap.min.css"
          integrity="sha384-GJzZqFGwb1QTTN6wy59ffF1BuGJpLSa9DkKMp0DgiMDm4iYMj70gZWKYbI706tWS" crossorigin="anonymous">
    <title>Products -- List</title>
</head>
<body>

<h1>Products List</h1>
<hr>
<div class="container">
    <table class="table table-striped table-bordered">
        <thead class="thead-light">
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Vendors</th>
            <th scope="col">Versions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${products}" var="product">
            <tr>
                <th scope="row"><c:out value="${product.id}"/></th>
                <td><c:out value="${product.name}"/></td>
                <td>
                    <visor:joinList list="${product.vendors}" delimiter=", " var="vendor">${vendor.id}</visor:joinList>
                </td>
                <td>
                    <visor:joinList list="${product.versions}" delimiter=", " var="version">${version.version}</visor:joinList>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>