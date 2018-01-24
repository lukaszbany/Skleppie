<%@ page import="skleppie.model.User" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="header.jsp" />

<div class="container">
    <h1>Rejestracja</h1>

    <div class="row">
        <div class="col-xs-6">
            <form:form action="registration" method="post" modelAttribute="user" >
                <div class="form-group">
                    <label class="control-label">E-mail*:</label> <form:input path="email" cssClass="form-control" cssErrorClass="form-control error" /> <form:errors path="email" cssClass="label label-danger" />
                </div>
                <div class="form-group">
                    <label class="control-label">Hasło*:</label> <form:password path="password" cssClass="form-control" cssErrorClass="form-control error"  /> <form:errors path="password" cssClass="label label-danger" />
                </div>
                <div class="form-group">
                    <label class="control-label">Imię*:</label> <form:input path="firstName" cssClass="form-control" cssErrorClass="form-control error" /> <form:errors path="firstName" cssClass="label label-danger" />
                </div>
                <div class="form-group">
                    <label class="control-label">Nazwisko*:</label> <form:input path="lastName" cssClass="form-control" cssErrorClass="form-control error"  /> <form:errors path="lastName" cssClass="label label-danger" />
                </div>
                <div class="form-group">
                    <label class="control-label">Ulica:</label> <form:input path="street" cssClass="form-control" />
                </div>
                <div class="form-group">
                    <label class="control-label">Kod pocztowy:</label> <form:input path="postalCode" cssClass="form-control" />
                </div>
                <div class="form-group">
                    <label class="control-label">Miasto:</label> <form:input path="city" cssClass="form-control" />
                </div>

                <input type="submit" value="Utwórz konto" class="btn btn-primary" />
            </form:form>

            <p class="bg-success">${successMessage}</p>
        </div>
    </div>

    <p>Pola oznaczone * są wymagane.</p>
</div>

<jsp:include page="footer.jsp" />

