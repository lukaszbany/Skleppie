<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp" />

<div class="container">
    <h3 class="bg-success">${successMessage}</h3>
    <h3 class="bg-warning">${warningMessage}</h3>
    <a role="button" href="${backLink}" class="btn btn-success">Powrót</a>
</div>

<jsp:include page="../footer.jsp" />

