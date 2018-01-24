<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="header.jsp" />

<div class="container">
    <h1>Skleppie - fajny sklep internetowy</h1>
    <c:if test="${not empty message}">
        <p class="bg-success">${message}</p>
    </c:if>
</div>

<jsp:include page="footer.jsp" />

