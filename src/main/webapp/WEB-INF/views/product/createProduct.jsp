<%@ page contentType="text/html;charset=UTF-8" import="com.supervisor.util.constant.ControllerMapping" %>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Create Product</title>
</head>
<body>
<%--@elvariable id="product" type="com.supervisor.domain.product.Product"--%>
<form:form method="POST" action="${ControllerMapping.PRODUCT_CONTROLLER_ROOT}/save" modelAttribute="product">
    <form:input path="name" type="text"/>
    <form:errors path="name"/>

    <input type="submit" value="Submit" />
</form:form>
</body>
</html>
