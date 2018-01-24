<%@ page import="skleppie.model.User" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="header.jsp" />

<div class="container">
    <h1>Logowanie</h1>
    <div class="row">
        <div class="col-xs-6">
            <form:form action="login" method="post" >
                <div class="form-group">
                    <label class="control-label">E-mail:</label> <input name="email" class="form-control"/>
                </div>
                <div class="form-group">
                    <label class="control-label">Hasło:</label> <input type="password" name="password" class="form-control" />
                </div>

                <input type="submit" value="Zaloguj" class="btn btn-primary" />
            </form:form>

            <c:if test="${param.error}">
                <p class="bg-danger">Błąd logowania.</p>
            </c:if>

        </div>
    </div>
</div>

<jsp:include page="footer.jsp" />

