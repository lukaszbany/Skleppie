<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp" />

<div class="container">
    <h1>Lista kategorii:</h1>
    <div class="panel panel-default">
        <div class="panel-heading">
            Pomoc:
            <button type="button" class="btn btn-info"><i class="fas fa-plus"></i></button> - dodawanie podkategorii
            <button type="button" class="btn btn-info"><i class="fas fa-trash"></i></button> - modyfikacja kategorii
            <button type="button" class="btn btn-danger"><i class="fas fa-pencil-alt"></i></button> - usuwanie kategorii
        </div>
        <div class="panel-body">
            <table class="table table-striped table-hover">
                <tr>
                    <th>id</th>
                    <th>nazwa</th>
                    <th>opis</th>
                    <th>kategoria nadrzędna</th>
                    <th>operacje</th>
                </tr>
                <c:forEach items="${categories}" var="category" >
                    <tr>
                        <td>${category.getId()}</td>
                        <td>
                                ${nestPrefixes.get(category.getId())} ${category.getName()}
                        </td>
                        <td>${category.getDescription()}</td>
                        <td>${category.getParent().getName()}</td>
                        <td>
                            <a role="button" href="/admin/categories/add?parentId=${category.getId()}" class="btn btn-info"><i class="fas fa-plus"></i></a>
                            <c:if test="${category.getId() != '1'}">
                                <a role="button" href="/admin/categories/${category.getId()}" class="btn btn-info"><i class="fas fa-pencil-alt"></i></a>
                                <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#remove${category.getId()}"><i class="fas fa-trash"></i></button>

                                <!-- Modal -->
                                <div class="modal fade" id="remove${category.getId()}" role="dialog">
                                    <div class="modal-dialog">

                                        <!-- Modal content-->
                                        <div class="modal-content">
                                            <div class="modal-header">
                                                <button type="button" class="close" data-dismiss="modal">&times;</button>
                                                <h4 class="modal-title">Potwierdź operację</h4>
                                            </div>
                                            <div class="modal-body">
                                                <p>Czy jesteś pewien, że chcesz usunąć <strong>${category.getName()}</strong> (i podkategorie)?</p>
                                            </div>
                                            <div class="modal-footer">
                                                <a role="button" class="btn btn-success" href="/admin/categories/remove?currentId=${category.getId()}">Usuń</a>
                                                <button type="button" class="btn btn-danger" data-dismiss="modal">Nie usuwaj</button>
                                            </div>
                                        </div>

                                    </div>
                                </div>

                            </c:if>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</div>


<jsp:include page="../footer.jsp" />

