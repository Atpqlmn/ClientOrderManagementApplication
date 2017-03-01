var ordersUrl = "http://localhost:8080/orders/show";


$(document).ready(function () {
    renderOrdersPage(ordersUrl, true);

});
// TODO: find how to best pass js arguments
/**
 * Renders the orders page based on the provided url and a sort term (tells how to sort results)
 * @param {String} url
 * @param {Boolean} [rendersFirstTime=false]
 * @param {String} [sortTerm="default_orders_sort"]
 * */
function renderOrdersPage(url, rendersFirstTime, sortTerm) {
    // console.log(rendersFirstTime);
    var tableBodyId = 'orders_body_results';
    if (sortTerm === undefined) {
        sortTerm = "default_orders_sort";
    }
    if (rendersFirstTime === undefined) {
        rendersFirstTime = false;
    }
    $.getJSON(url, {"sortTerm": sortTerm}, function (data) {
        var result = parseIntoTableBody(tableBodyId, data);
        if (!rendersFirstTime) {
            $("#" + tableBodyId).remove();
        }
        $("#orders_table_results").append(result);
        $("#" + tableBodyId).show("normal");

    });
}


/**
 * if a new sort option is chosen the function is triggered and
 * sorts results in the way chosen by a user */
function sortOrders() {
    var sortTerm = document.getElementById("sort_orders_select_id").value;
    renderOrdersPage(ordersUrl, false, sortTerm);
}
/**
 * Parses JSON from orders into string that will be later appended
 * @param tableBodyId - id of the created table body
 * @param data - JSON array to be parsed
 * */
function parseIntoTableBody(tableBodyId, data) {
    var result = "<tbody id='" + tableBodyId + "' style='display: none'>";
    for (var i = 0; i < data.length; i++) {
        result += "<tr>";
        result += "<td>" + data[i]["orderNumber"] + "</td>";
        result += "<td>" + data[i]["orderTransactionDate"] + "</td>";
        result += "<td>" + data[i]["client"]["clientFirstName"] + "</td>";
        result += "<td>" + data[i]["client"]["clientLastName"] + "</td>";
        result += "<td>" + data[i]["product"]["productName"] + "</td>";
        result += "<td>" + data[i]["product"]["productBarcode"] + "</td>";
        result += "<td>" + data[i]["orderPrice"] + "</td>";
        result += "</tr>";
    }
    result += "</tbody>";
    return result;
}