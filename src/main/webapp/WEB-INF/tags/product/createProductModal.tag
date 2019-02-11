<%@ tag description="Returns the create products modal" body-content="empty" isELIgnored="false"
        pageEncoding="UTF-8" import="com.supervisor.util.constant.ControllerMapping" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

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
                <jsp:useBean id="command" scope="request" class="com.supervisor.command.ProductSaveCommand"/>
                <form:form method="POST" action="${ControllerMapping.PRODUCT_CONTROLLER_ROOT}/save"
                           id="createProductForm" novalidate="true">
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
                <button type="button" class="btn btn-primary">Save changes</button>
            </div>
        </div>
    </div>
</div>

<script>
    (function () {
        'use strict';
        window.addEventListener('load', function () {
            let form = document.getElementById("createProductForm");
            form.addEventListener('submit', function (event) {

                /*if (form.checkValidity() === false) {
                    event.preventDefault();
                    form.classList.add('was-validated');
                    event.stopPropagation();
                    return false;
                } else {
                    form.classList.remove('was-validated');
                }*/
            });
        });
    })();
</script>