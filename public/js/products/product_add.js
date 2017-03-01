$('#product_form').submit(function () {
    $.post("http://localhost:8080/products/product/add", $("#product_form").serialize(), function () {
        window.location.replace("products.html");
    });
    return false;
});

