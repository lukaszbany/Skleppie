<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp" />

<div class="container">
    <h1>Lista produktów:</h1>
    <div class="panel panel-default">
        <div class="panel-heading">
            Pomoc:
            <button type="button" class="btn btn-info" disabled><i class="fas fa-trash"></i></button> - modyfikacja produktu
            <button type="button" class="btn btn-danger" disabled><i class="fas fa-pencil-alt"></i></button> - usuwanie produktu
            </br>
            </br>
            <form:form action="/admin/products" method="get" cssClass="form-inline product-filter">
            Operacje:
            <button type="button" class="btn btn-info"><i class="fas fa-plus"></i> Dodaj produkt</button>
            Filtr kategorii:
                <select name="category" class="form-control">
                    <c:forEach items="${categories}" var="category">
                        <c:choose>
                            <c:when test="${category == currentCategory}">
                                <option value="${category.getId()}" selected>${category.getName()}</option>
                            </c:when>
                            <c:when test="${category != currentCategory}">
                                <option value="${category.getId()}">${category.getName()}</option>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </select>
                <button type="submit" value="Filtruj" class="btn btn-primary">Filtruj</button>

            <a role="button" href="/admin/products" class="btn btn-danger">Wszystkie produkty</a>
            </form:form>
        </div>
        <div class="panel-body">
            <table class="table table-striped table-hover">
                <tr>
                    <th>id</th>
                    <th>nazwa</th>
                    <th>opis</th>
                    <th>kategoria</th>
                    <th>cena</th>
                    <th>ilość</th>
                    <th>zdjęcie</th>
                    <th>operacje</th>
                </tr>
                <c:forEach items="${products}" var="product" >
                    <tr>
                        <td>${product.getId()}</td>
                        <td class="col-md-2">${product.getName()}</td>
                        <td class="col-md-4">${product.getDescription()}</td>
                        <td class="col-md-2">${product.getCategory().getName()}</td>
                        <td>${product.getPrice()} zł</td>
                        <td>${product.getQuantity()}</td>
                        <td><img src="/public/images/products/${product.getImageFilename()}" class="img-thumbnail admin-panel-products"> </td>
                        <td class="col-md-2">
                            <a role="button" href="/admin/products/${product.getId()}" class="btn btn-info"><i class="fas fa-pencil-alt"></i></a>&nbsp;
                            <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#remove${product.getId()}"><i class="fas fa-trash"></i></button>

                            <!-- Modal -->
                            <div class="modal fade" id="remove${product.getId()}" role="dialog">
                                <div class="modal-dialog">

                                    <!-- Modal content-->
                                    <div class="modal-content">
                                        <div class="modal-header">
                                            <button type="button" class="close" data-dismiss="modal">&times;</button>
                                            <h4 class="modal-title">Potwierdź operację</h4>
                                        </div>
                                        <div class="modal-body">
                                            <p>Czy jesteś pewien, że chcesz usunąć <strong>${product.getName()}</strong>?</p>
                                        </div>
                                        <div class="modal-footer">
                                            <a role="button" class="btn btn-success" href="/admin/products/remove?currentId=${category.getId()}">Usuń</a>
                                            <button type="button" class="btn btn-danger" data-dismiss="modal">Nie usuwaj</button>
                                        </div>
                                    </div>

                                </div>
                            </div>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>


<jsp:include page="../footer.jsp" />

