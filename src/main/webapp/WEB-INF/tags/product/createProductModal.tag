<%@ tag description="Returns the create products modal" body-content="empty" isELIgnored="false" pageEncoding="UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="visor" uri="http://www.supervisor.com/tags/core" %>

<%@ attribute name="modalId" required="true" type="java.lang.String"
              rtexprvalue="true" description="The ID that's going to be used as the modal ID" %>

<div class="modal fade" id="${modalId}" tabindex="-1" role="dialog"
     aria-labelledby="createProductLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="createProductLabel">Create Product</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <jsp:useBean id="productController" class="com.supervisor.controller.ProductController"/>
                <c:set var="productSave"><visor:reverseUrl controller="${productController['class']}" action="createProduct"/></c:set>
                <jsp:useBean id="command" scope="request" class="com.supervisor.command.ProductSaveCommand"/>
                <form:form method="POST" action="${productSave}" id="createProductForm" novalidate="true">
                    <div class="form-group">
                        <form:label path="name">Name</form:label>
                        <form:input path="name" placeholder="Product name" cssClass="form-control" required="true"/>
                        <div class="invalid-feedback">
                            <spring:message code="product.Product.name.notBlank"/>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-primary">Submit</button>
                </form:form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>

<script>
    SUP = SUP || {};
    $(function () {
        'use strict';
        window.addEventListener('load', function () {
            let form = document.getElementById("createProductForm");
            form.addEventListener('submit', function (event) {
                event.preventDefault();

                if (form.checkValidity() === false) {
                    event.stopPropagation();
                    form.classList.add('was-validated');
                    return false;
                } else {
                    form.classList.remove('was-validated');
                    createProduct();
                }
            });
        });

        function createProduct() {
            let form = $("#createProductForm");
            let data = form.serializeArray();
            let url = form.attr("action");

            $.post({
                "url": url,
                "data": data,
                "dataType": "application/json"
            }).done(function (response) {
                response = SUP.ajaxHelper.extractJson(response);
                console.log(response);
            }).fail(function (response) {
                response = SUP.ajaxHelper.extractJson(response);
                console.log(response);
            });
        }
    });
</script>
