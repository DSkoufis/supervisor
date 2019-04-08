<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="tt" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="product" tagdir="/WEB-INF/tags/product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="modalId" value="createProductModal"/>
<c:set var="products" value="${requestScope.products}"/>
<c:remove var="products" scope="request"/>

<tt:main>
    <jsp:attribute name="header">
        <title>Products - List</title>
    </jsp:attribute>

    <jsp:attribute name="customImports">
        <tt:customLibrary fileName="/ajaxHelper.js"/>
    </jsp:attribute>

    <jsp:body>
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
            <table class="table table-striped table-bordered" id="productsList">
                <thead class="thead-light">
                    <jsp:include page="_productListItem.jsp">
                        <jsp:param name="isHeader" value="${true}"/>
                    </jsp:include>
                </thead>
                <tbody>
                    <c:forEach items="${products}" var="product" varStatus="loopInfo">
                        <c:set var="loopInfo" value="${loopInfo}" scope="request"/>
                        <c:set var="product" value="${product}" scope="request"/>
                        <jsp:include page="_productListItem.jsp">
                            <jsp:param name="isHeader" value="${false}"/>
                        </jsp:include>

                        <c:remove var="loopInfo" scope="request"/>
                        <c:remove var="product" scope="request"/>
                    </c:forEach>
                </tbody>
            </table>
        </div>

        <jsp:include page="_createProductModal.jsp">
            <jsp:param name="modalId" value="${modalId}"/>
        </jsp:include>
    </jsp:body>
</tt:main>