<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="../header.jsp" />

<div class="container">
    <h1>Zdjęcia produktów:</h1>
    <h3 class="bg-success">${successMessage}</h3>
    <h3 class="bg-warning">${warningMessage}</h3>
    <div class="panel panel-default">
        <div class="panel-heading">
            <form:form cssClass="form-inline" enctype="multipart/form-data">
                <input type="file" name="file">
                <br/>
                <button type="submit" class="btn btn-info"><i class="fas fa-plus"></i> Dodaj zdjęcie</button>
            </form:form>
        </div>
        <div class="panel-body">
            <div class="row">
                <c:forEach items="${files}" var="file">
                    <div class="col-xs-12 col-sm-4 col-md-3">
                            <div class="thumbnail text-center">
                                <img src="/public/images/products/${file}" class="img-thumbnail admin-panel-pictures">
                                <div class="caption">
                                    <c:choose>
                                        <c:when test="${file.length() > 18}" >
                                            <h4 alt="${file}">${fn:substring(file, 0, 18)}...</h4>
                                        </c:when>
                                        <c:when test="${file.length() <= 18}" >
                                            <h4>${file}</h4>
                                        </c:when>
                                    </c:choose>
                                    <p><a href="#" class="btn btn-danger" role="button" data-toggle="modal" data-target="#remove" onclick="preparePictureRemoveModal('${file}')"><i class="fas fa-trash"></i> Usuń zdjęcie</a></p>
                                </div>
                            </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>

<!-- Modal -->
<div class="modal fade" id="remove" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Potwierdź operację</h4>
            </div>
            <div class="modal-body">
                <p>Czy jesteś pewien, że chcesz usunąć <strong><span id="filenameModal">{picture}</span></strong>?</p>
            </div>
            <div class="modal-footer">
                <a role="button" id="pictureRemoveLinkModal" class="btn btn-success" href="/admin/pictures/remove?filename=">Usuń</a>
                <button type="button" class="btn btn-danger" data-dismiss="modal">Nie usuwaj</button>
            </div>
        </div>

    </div>
</div>

<jsp:include page="../footer.jsp" />

