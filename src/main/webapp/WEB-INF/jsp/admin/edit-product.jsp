<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<jsp:include page="../header.jsp" />

<div class="container">
    <h1>Edycja produktu</h1>

    <div class="row">
        <div class="col-xs-6">
            <form:form action="${action}" method="post" modelAttribute="product">

                <div class="form-group">
                    <label class="control-label">Nazwa produktu:</label> <form:input path="name" cssClass="form-control" cssErrorClass="form-control error" /> <form:errors path="name" cssClass="label label-danger" />
                </div>

                <div class="form-group">
                    <label class="control-label">Opis:</label> <form:textarea path="description" cssClass="form-control" cssErrorClass="form-control error" /> <form:errors path="description" cssClass="label label-danger" />
                </div>

                <div class="form-group">
                    <label class="control-label">Kategoria:</label>
                    <form:select path="category" cssClass="form-control" >
                        <c:forEach items="${categories}" var="category" >
                            <c:choose>
                                <c:when test="${category.getId() == currentCategory.getId()}" >
                                    <option value="${category.getId()}" selected="true">${category.getName()}</option>
                                </c:when>
                                <c:when test="${category.getId() != currentCategory.getId()}" >
                                    <option value="${category.getId()}">${category.getName()}</option>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </form:select>
                </div>

                <div class="form-group">
                    <label class="control-label">Cena:</label> <form:input path="price" cssClass="form-control" cssErrorClass="form-control error" /> <form:errors path="price" cssClass="label label-danger" />
                </div>

                <div class="form-group">
                    <label class="control-label">Dostępna ilość:</label> <form:input path="quantity" cssClass="form-control" cssErrorClass="form-control error" /> <form:errors path="quantity" cssClass="label label-danger" />
                </div>

                <div class="form-group">
                    <label class="control-label">Zdjęcie produktu:</label>
                    <form:select path="imageFilename" cssClass="form-control" id="imageFilename" onchange="imageRefresh()">
                        <c:forEach items="${images}" var="image" >
                            <c:choose>
                                <c:when test="${image == product.getImageFilename()}" >
                                    <option value="${image}" selected="true">${image}</option>
                                </c:when>
                                <c:when test="${image != product.getImageFilename()}" >
                                    <option value="${image}">${image}</option>
                                </c:when>
                            </c:choose>
                        </c:forEach>
                    </form:select>

                    <c:choose>
                        <c:when test="${product.getImageFilename() == null }">
                            <a data-toggle="modal" id="admin-product-image-zoom" href="" onclick="imageZoomRefresh()"><img src="/public/images/products/${images.get(0)}" class="img-thumbnail admin-panel-products" id="image-thumbnail" ></a>
                        </c:when>
                        <c:when test="${product.getImageFilename() != null }">
                            <a data-toggle="modal" id="admin-product-image-zoom" href="" onclick="imageZoomRefresh()"><img src="/public/images/products/${product.getImageFilename()}" class="img-thumbnail admin-panel-products" id="image-thumbnail"></a>
                        </c:when>
                    </c:choose>

                </div>

                <input type="submit" value="Zapisz" class="btn btn-primary" />
                <a role="button" href="/admin/products" class="btn btn-danger">Anuluj</a>

            </form:form>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="admin-product-image-zoom" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title"></h4>
            </div>
            <div class="modal-body">
                <img src="" class="image-zoom" id="modal-image-zoom"/>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Zamknij</button>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../footer.jsp" />

