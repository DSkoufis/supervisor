<%@ tag description="Main Navigation bar" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="visor" uri="http://www.supervisor.com/tags/core" %>

<nav class="navbar navbar-expand-md navbar-dark bg-dark">
    <div class="navbar-header pr-4 pl-2">
        <a class="navbar-brand" href="<c:url value="/"/>">Visor</a>
    </div>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarContents"
            aria-controls="navbarContents" aria-expanded="false" aria-label="Expand Content">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarContents">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item">
                <a class="nav-link" href="<c:url value="/" />">Home</a>
            </li>
            <li class="nav-item">
                <jsp:useBean id="productController" class="com.supervisor.controller.ProductController"/>
                <a class="nav-link" href="<visor:reverseUrl controller="${productController['class']}" action="index"/>">Products</a>
            </li>

            <%--<li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown"
                   aria-haspopup="true" aria-expanded="false">
                    Dropdown
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="#">Action</a>
                    <a class="dropdown-item" href="#">Another action</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="#">Something else here</a>
                </div>
            </li>
            <li class="nav-item">
                <a class="nav-link disabled" href="#" tabindex="-1" aria-disabled="true">Disabled</a>
            </li>--%>
        </ul>
        <%--<form class="form-inline my-2 my-lg-0">
            <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
            <button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
        </form>--%>
    </div>
</nav>