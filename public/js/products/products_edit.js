$(document).ready(function () {
    populateClientInformation();

});

function populateClientInformation() {
    var productBarcode = sessionStorage.productBarcode;
    sessionStorage.removeItem("productBarcode");
    console.log(productBarcode);
    if (productBarcode === undefined) {
        $("#productInfo").text("No product information was chosen to edit");
        $("form")[0].reset();
    } else {
        $.getJSON("http://localhost:8080/products/product/" + productBarcode, function (data) {
            document.getElementById("productBarcode").value = productBarcode;
            document.getElementById("productName").value = data["productName"];
            document.getElementById("productBasePrice").value = data["productBasePrice"];
            document.getElementById("productDescription").value = data["productDescription"];
            document.getElementById("productReleaseDate").value = data["productReleaseDate"];
        });
    }
}

$('#product_form').submit(function () {
    $.post("http://localhost:8080/products/product/edit", $("#product_form").serialize(), function () {
        window.location.replace("products.html");
    });
    return false;
});
