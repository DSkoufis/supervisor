<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="form-group" id="formResponse">
    <label for="name">Name</label>
    <input type="text" class="form-control" id="name" placeholder="Product name" name="name" required>
    <div class="invalid-feedback">
        <c:choose>
            <c:when test="${requestScope.errors != null}">
                <c:forEach items="${requestScope.errors}" var="error">
                    <c:choose>
                        <c:when test="${error.code}">
                            <spring:message code="${error.code}"/><br>
                        </c:when>
                        <c:otherwise>
                            ${error.message}<br>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <spring:message code="product.Product.name.notBlank"/><br>
            </c:otherwise>
        </c:choose>
    </div>
</div>

