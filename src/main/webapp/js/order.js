$("select").change(function(){
$("#card-data").hide();
if($("#credit-cart").is(':selected')){
$("#card-data").show();
}
});
