var removeLink;
var indexOfFilename;
var productImagesPath;
var imageFilename;
var pictureRemoveLink;


$(document).ready(function() {
    var currentViewName = window.location.pathname;
    var lastIndexOfSeparator = currentViewName.lastIndexOf('/');

    if (currentViewName === '/admin/pictures') {
        pictureRemoveLink = $("a#pictureRemoveLinkModal").attr("href");
    }else if (currentViewName === '/admin/products' || currentViewName === '/admin/categories') {
        removeLink = $("a#linkModal").attr("href");
    }else if  (currentViewName.substr(0, lastIndexOfSeparator + 1) === '/admin/products/') {
        indexOfFilename = $("img#image-thumbnail").attr("src").lastIndexOf("/");
        productImagesPath = $("img#image-thumbnail").attr("src").substr(0, indexOfFilename + 1)
        imageFilename = $("img#image-thumbnail").attr("src").substr(indexOfFilename + 1);
    }

});


function prepareRemoveModal(id, name) {
    $("span#nameModal").html(name);
    $("a#linkModal").attr("href", removeLink + id);
};

function preparePictureRemoveModal(modalFilename) {
    $("span#filenameModal").html(modalFilename);
    $("a#pictureRemoveLinkModal").attr("href", pictureRemoveLink + modalFilename);
};

function imageRefresh() {
    var imageFilename = $("select#imageFilename").find(":selected").text();
    $("img#image-thumbnail").attr("src", productImagesPath + imageFilename);
};

function imageZoomRefresh() {
    $("a#admin-product-image-zoom").attr("href", productImagesPath + imageFilename);
};