<%@ page contentType="text/html;charset=UTF-8" import="com.supervisor.util.constant.ViewMapping" %>

<%@ taglib prefix="tt" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="modalId" value="createProductModal"/>
<c:set var="productViewsPath" value="${ViewMapping.PRODUCT_VIEW_PATH_FULL}"/>

<tt:main>
    <jsp:attribute name="header">
        <title>Products - List</title>
    </jsp:attribute>

    <jsp:attribute name="customImports">
        <tt:customLibrary fileName="/ajaxHelper.js" />
    </jsp:attribute>

    <jsp:body>
        <jsp:useBean id="products" scope="request" type="java.util.List"/>

        <div class="row align-items-center">
            <div class="col-md-4">
                <h1>Products List</h1>
            </div>
            <div class="col-md-2 offset-md-6">
                <button type="button" class="btn btn-info" data-toggle="modal" data-target="#${modalId}">
                    Create Product
                </button>
            </div>
        </div>
        <hr>
        <div class="container">
            <c:import url="${productViewsPath}_productsTable.jsp">
                <c:param name="products" value="${products}"/>
            </c:import>
        </div>

        <product:createProductModal modalId="${modalId}"/>
    </jsp:body>
</tt:main>