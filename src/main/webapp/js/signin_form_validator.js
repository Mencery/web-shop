document.getElementById(SIGN_IN_SUBMIT).onclick = function () {
	var form = document.getElementById(SIGN_IN);
		form.submit();
}
function areInputsEmpty(){
console.log(3);
var inputs = document.getElementsByTagName(TAG_INPUT);
console.log(inputs);
for(var i =0;i<inputs.length;i++){
	if(!inputs[i].value){
	console.log(inputs[i]);
		inputs[i].setAttribute(CLASS,  FORM_CONTROL+ SPACE +NOT_VALID);
		return true;
	}
}
  return false;
}
function validSign(event) {
	event.preventDefault();
	validByPattern(event.target);
	function validByPattern(element) {
		var value = element.value;
		var pattern = element.getAttribute(DATA_PATTERN);
		checkValid(element, value.match(pattern) == value);
	}

}
function checkValid(element, expression) {
	if (expression) {
		element.setAttribute(CLASS, FORM_CONTROL+ SPACE + VALID);
	}
	else {
		element.setAttribute(CLASS, FORM_CONTROL+ SPACE + NOT_VALID);
	}
}
var controls = document.getElementById(SIGN_IN).getElementsByClassName(FORM_CONTROL);
for (var i = 0; i < controls.length; i++) {
	controls[i].addEventListener(BLUR, validSign, false);
}


