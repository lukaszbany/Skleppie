<%@ page import="skleppie.model.User" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="../header.jsp" />

<div class="container">
    <h1>Rejestracja</h1>

    <div class="row">
        <div class="col-xs-6">
            <form:form action="${action}" method="post" modelAttribute="category" >
                <div class="form-group">
                    <label class="control-label">Nazwa kategorii:</label> <form:input path="name" cssClass="form-control" cssErrorClass="form-control error" /> <form:errors path="name" cssClass="label label-danger" />
                </div>
                <div class="form-group">
                    <label class="control-label">Opis:</label> <form:input path="description" cssClass="form-control" />
                </div>
                <div class="form-group">
                    <label class="control-label">Kategoria nadrzędna*:</label>
                    <form:select path="parent" cssClass="form-control" >
                        <c:forEach items="${categories}" var="category" >
                            <c:choose>
                                <c:when test="${category.getId() == parentId}" >
                                    <option value="${category.getId()}" selected="true">${category.getName()}</option>
                                </c:when>
                                <c:when test="${category.getId() != parentId}" >
                                    <option value="${category.getId()}">${category.getName()}</option>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>

                <input type="submit" value="Zapisz" class="btn btn-primary" />
                <a role="button" href="/admin/categories" class="btn btn-danger">Anuluj</a>
            </form:form>
        </div>
    </div>
</div>

<jsp:include page="../footer.jsp" />

