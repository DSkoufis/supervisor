<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="tt" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/product" %>

<tt:main>
    <jsp:attribute name="header">
        <title>Products - List</title>
    </jsp:attribute>

    <jsp:body>
        <jsp:useBean id="products" scope="request" type="java.util.List"/>

        <h1>Products List</h1>
        <hr>
        <div class="container">
            <product:printProducts products="${products}"/>
        </div>
    </jsp:body>
</tt:main>