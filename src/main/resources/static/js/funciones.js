function showPassword() {
	var x = document.getElementById("inputPassword");
	if (x.type === "password") {
		x.type = "text";
	} else {
		x.type = "password";
	}
}

function check(input) {
	if (input.value != document.getElementById('inputPassword').value) {
		input.setCustomValidity('Las contraseñas deben coincidir');
	} else {
		// input is valid -- reset the error message
		input.setCustomValidity('');

	}
}

