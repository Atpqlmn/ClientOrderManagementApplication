$(document).ready(function () {
    renderPurchasePage();

});

function renderPurchasePage() {
    renderClients();
    renderProducts();
}

function renderClients() {
    $.getJSON("http://localhost:8080/clients/show", populateClients);
}
function renderProducts() {
    $.getJSON("http://localhost:8080/products/show", populateProducts);
}

function populateClients(jsonClients) {
    var result = "";
    for (var i = 0; i < jsonClients.length; i++) {
        var client = jsonClients[i];
        result += '<option value="' + client["clientSecurityId"] + '">'
            + client["clientFirstName"] + " " + client["clientLastName"] + '</option>';
    }
    $("#orderClient").append(result);
}
function populateProducts(jsonProducts) {
    var result = "";
    for (var i = 0; i < jsonProducts.length; i++) {
        var client = jsonProducts[i];
        result += '<option value="' + client["productBarcode"] + '">'
            + "product name: " + client["productName"] + "; "
            + "product barcode: " + client["productBarcode"] + '</option>';
    }
    $("#orderProduct").append(result);
}

function priceCalculation() {
    var quantityInput = document.getElementById("productQuantity").value;
    if (isPositiveInteger(quantityInput)) {
        var barcode = document.getElementById("orderProduct").value;
        calculatePriceOfChosenProduct(barcode, quantityInput);

    } else {
        document.getElementById("orderPrice").value = "Quantity must be a positive integer";
    }
}
function isPositiveInteger(n) {
    return 0 === n % (!isNaN(parseFloat(n)) && 0 < ~~n);
}
function calculatePriceOfChosenProduct(productBarcode, quantity) {
    return $.getJSON("http://localhost:8080/products/product/" + productBarcode, function (data) {
        document.getElementById("orderPrice").value = (Number(quantity)
            * Number(data["productBasePrice"])).toFixed(2);
    });
}

// TODO: Check it one more time
$('#purchase_form').submit(function () {
    $.post("http://localhost:8080/orders/order/add", $("#purchase_form").serialize(), function(){
        window.location = "purchase.html";
    });
});
