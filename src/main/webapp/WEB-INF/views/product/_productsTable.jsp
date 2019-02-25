<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/product" %>
<%@ taglib prefix="visor" uri="http://www.supervisor.com/tags/core" %>

<jsp:useBean id="products" scope="request" type="java.util.List"/>

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
    <product:printProducts products="${products}"/>
    </tbody>
</table>