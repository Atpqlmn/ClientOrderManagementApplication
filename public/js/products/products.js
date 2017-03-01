$(document).ready(function () {
    renderProductsPage();

});


function renderProductsPage() {

    $.getJSON("http://localhost:8080/products/show", function (data) {
        var result = "<tbody>";
        for (var i = 0; i < data.length; i++) {
            result += "<tr>";
            result += "<td>" + data[i]["productBarcode"] + "</td>";
            result += "<td>" + data[i]["productName"] + "</td>";
            result += "<td>" + data[i]["productBasePrice"] + "</td>";
            result += "<td>" + data[i]["productDescription"] + "</td>";
            result += "<td>" + data[i]["productReleaseDate"] + "</td>";
            result += '<td>' +
                '<button type="button" class="btn btn-info" ' +
                'onclick=editProduct("' + data[i]["productBarcode"] + '")>Edit</button>' +
                '</td>';
            result += "</tr>"
        }
        result += "</tbody>";
        $("#products_table_results").append(result);
    });

}

function addProduct() {
    window.location = "product_add.html";
}

// submits an Id to the server, then server redirects to another page
function editProduct(productBarcode) {
    sessionStorage.productBarcode = productBarcode;
    window.location = "products_edit.html";
}