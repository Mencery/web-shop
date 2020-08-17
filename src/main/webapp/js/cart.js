var cart = {};
$('document').ready(function () {
    checkCart();
    showCart();
    sendToServlet();
});
$(".p-add[data-id]").on(CLICK, function () {
    var id = $(this).attr('data-id');
    if (cart[id] != undefined) {
        cart[id]++;
    }
    else {
        cart[id] = 1;
    }

    localStorage.setItem("pageCart", JSON.stringify(cart));

    showCart();
    sendToServlet();
});
function checkCart() {

    if (localStorage.getItem("pageCart") != null) {
        cart = JSON.parse(localStorage.getItem("pageCart"));
    }
}
function showCart() {
    var out = '';
    var amount=0;
    for (var key in cart) {
        out += "key " + key + " has value " + cart[key];
        amount++;
    }

    $('#cart-products').html(amount.toString());

}

function sendToServlet(){
 $.ajax({
            url: '/shop/cart.do',
            data: {"pageCart":JSON.stringify(cart)}
  });
}
$(".amount").change( function(){
 var id = $(this).attr('data-id');
 cart[id] = $(this).val();
 localStorage.setItem("pageCart", JSON.stringify(cart));
 sendToServlet();
 var price =  parseInt($(".price[data-id ="+id+"]").text());
$(".summary[data-id ="+id+"]").html("  =  "+(cart[id]*price)+"$");
});
$(".delete").on(CLICK, function(){
var id = $(this).attr('data-id');
delete cart[id];
localStorage.setItem("pageCart", JSON.stringify(cart));
$("tr[data-id="+id+"]").remove();
 $.ajax({
            url: '/shop/cartdelete.do',
            data: {"deleteProductId":id}
  });
});
$(".clear").on(CLICK, function(){
cart = {};
localStorage.setItem("pageCart", JSON.stringify(cart));
$("tr").remove();
$(".cart").html("Empty");
 $.ajax({
            url: '/shop/cartclear.do'
  });
});
$(".order").on(CLICK, function(){
cart = {};
localStorage.setItem("pageCart", JSON.stringify(cart));
});



