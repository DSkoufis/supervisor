<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="visor" uri="http://www.supervisor.com/tags/core" %>

<jsp:useBean id="productController" class="com.supervisor.controller.ProductController"/>
<c:set var="productSave"><visor:reverseUrl controller="${productController['class']}" action="createProduct"/></c:set>

<div class="modal fade" id="${param.modalId}" tabindex="-1" role="dialog"
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
                <jsp:useBean id="command" scope="request" class="com.supervisor.command.ProductSaveCommand"/>
                <form:form method="POST" action="${productSave}" id="createProductForm" novalidate="true">
                    <jsp:include page="_createProductForm.jsp"/>

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
    $(function () {
        'use strict';
        window.addEventListener('load', function () {
            var form = document.getElementById("createProductForm");
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
            var form = $("#createProductForm");
            var data = form.serializeArray();
            var url = form.attr("action");

            $.post({
                "url": url,
                "data": data
            }).done(function (response) {
                $("#productsList > tbody").append(response);
                $('#${param.modalId}').modal('hide');
            }).fail(function (response) {
                $('#formResponse').html(response.responseText);
                form.addClass("was-validated");
            });
        }
    });
</script>