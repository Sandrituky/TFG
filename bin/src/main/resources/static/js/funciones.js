function showPassword() { // dropped
	var x = document.getElementById("inputPassword");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}

function check(input) { // comprueba que las contraseñas coincidan
	if (input.value != document.getElementById('password1').value) {
		input.setCustomValidity('Las contraseñas deben coincidir');
	} else {
		// input is valid -- reset the error message
		input.setCustomValidity('');

	}
}



