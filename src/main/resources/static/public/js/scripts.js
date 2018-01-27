var modalID;
var modalName;
var removeLink = $("a#linkModal").attr("href");
var productImagesPath = "/public/images/products/";

function prepareModal() {
    $("span#nameModal").html(modalName);
    $("a#linkModal").attr("href", removeLink + modalID);
}

function imageRefresh() {
    var imageFilename = $("select#imageFilename").find(":selected").text();
    $("img#image-thumnbnail").attr("src", productImagesPath + imageFilename);
}