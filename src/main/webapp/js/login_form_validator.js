$(ID_LOG_IN_SUBMIT).on(CLICK, function () {
	if ($(ID_LOGIN+SPACE+CLASS_NOT_VALID).toArray().length == 0 && !areInputsEmpty() ) {
		$(ID_LOGIN).submit();
	}
});
function areInputsEmpty(){
	var inputs = $(TAG_INPUT);
	for(var i =0;i<inputs.length;i++){
		if(!inputs[i].value){
			inputs[i].setAttribute(CLASS,  FORM_CONTROL+ SPACE +NOT_VALID);
			return true;
		}
	}
	  return false;
	}
$(ID_LOGIN+SPACE+CLASS_FORM_CONTROL).on(BLUR, function (e) {
	valid($(e.currentTarget));
});
function valid(form_control) {
	var value = form_control.val();
	var pattern = form_control.attr(DATA_PATTERN);
	if ( value.match(pattern) == value) {
		form_control.attr(CLASS, FORM_CONTROL+ SPACE + VALID);
	}
	else {
		form_control.attr(CLASS,FORM_CONTROL+ SPACE +NOT_VALID);
	}
}

