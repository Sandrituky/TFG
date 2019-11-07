//___________USUARIOS________________________
$(document).ready(function() { // valida que el correo tenga bien el formato y sea unico
	$('#email').keyup(function() {

		var inputEmail = $("#email");
		var email = $('#email').val();

		if (email != '') {

			inputEmail.addClass('loading');

			$.ajax({
				url : 'http://localhost:8080/json/checkemail/',
				data : {
					email : $(this).val()
				},
				type : 'GET',
				dataType : 'json',
				success : function(json) {

					setTimeout(function() {
						inputEmail.removeClass('loading');
					}, 1000);

					if (json.result == true) { // DNI registrado
						inputEmail[0].setCustomValidity('Este email ya está registrado');

					} else if (checkEmail(email) == false) { //

					} else {

					}

				},
				error : function(jqXHR, status, error) {

				},

				complete : function(jqXHR, status) {

				}
			});

		} else {

		}

	});
});



$(document).ready(function() { // valida que el DNI tenga bien el formato y sea unico
	$('#dni').keyup(function() {

		var dni = $('#dni').val();
		var inputDNI = $("#dni")[0];

		$('#status').html('<img id="loading" src="/imagenes/loading.gif">');

		if (dni != '') {

			$.ajax({
				url : 'http://localhost:8080/json/checkdni/',
				data : {
					dni : $(this).val()
				},
				type : 'GET',
				dataType : 'json',
				success : function(json) {
					if (json.result == true) { // DNI registrado
						inputDNI.setCustomValidity('Este DNI ya está registrado');

					} else if (checkDni(dni) == false) { //

					} else {

					}

				},
				error : function(jqXHR, status, error) {

				},

				complete : function(jqXHR, status) {

				}
			});

		} else {

		}

	});
});

$(document).ready(function() { // valida que el telefono tenga bien el formato y sea unico
	$('#telefono').keyup(function() {

		var telefono = $('#telefono').val();
		var inputTelefono = $("#telefono")[0];

		if (telefono != '') {

			$.ajax({
				url : 'http://localhost:8080/json/checktelefono/',
				data : {
					telefono : $(this).val()
				},
				type : 'GET',
				dataType : 'json',
				success : function(json) {
					if (json.result == true) { // Telefono registrado
						inputTelefono
								.setCustomValidity('Este telefono ya está registrado');

					} else if (checkTelefono(telefono) == false) { //

					} else {

					}

				},
				error : function(jqXHR, status, error) {

				},

				complete : function(jqXHR, status) {

				}
			});

		} else {
		}

	});
});

$(document).ready(function() { // pone la fecha como valor vacio
	if ($("#dni").length) { // comprueba que el elemento existe
		inputFnac = $('#fnac')[0];
		fnac = $('#fnac').val("");

		inputFnac.setCustomValidity('Debes introducir tu fecha de nacimiento');

	}
});

$(document).ready(function() { // comprueba la mayoría de edad
	if ($("#dni").length) {
		$('#fnac').keyup(function() {

			var today = moment();

			inputFnac = $("#fnac")[0];
			birthday = $("#fnac").val();

			var diferencia = today.diff(birthday, 'years');
			if (diferencia >= 18) {
				inputFnac.setCustomValidity('');
			} else {
				inputFnac.setCustomValidity('Debes ser mayor de edad');
			}

		});
	}
});

// _________________ANIMALES______________________
$(document).ready(function() { // pone todos los radioButton en unchecked
	if ($("#raza").length) { // comprueba que el elemento existe

		$('input[type="radio"]').prop('checked', false);
	}
});


$(document).ready(function() { // pone la fecha como valor vacio
		if ($("#raza").length) { // comprueba que el elemento existe
			
			inputFnac = $('#fnac')[0];
			fnac = $('#fnac').val("");
			
			var today = moment().format('YYYY-MM-DD');
			inputFnac.setAttribute("max", today);
			
			if (fnac == "") {
				inputFnac
						.setCustomValidity('Debes introducir la fecha de nacimiento del animal');
			} else {
				inputFnac.setCustomValidity('');
			}
		}
	});

$(document).ready(function() { // comprueba la edad del animal
	if ($("#raza").length) {
		$('#fnac').keyup(function() {

			var today = moment();

			inputFnac = $("#fnac")[0];
			birthday = $("#fnac").val();
			

			
			var diferencia = today.diff(birthday, 'years');
			
			if (diferencia <= 40) { // suponemos que es imposible que un animal tenga
				// mas de 40 años
				inputFnac.setCustomValidity('');
			} else {
				inputFnac.setCustomValidity('Debes ser mayor de edad');
			}

		});
	}
});

var filtroSelectAnimalesSexo = '';
var filtroSelectAnimalesTipo = '';

function aplicarFiltro(){
	$("#selectAnimal").children('option').hide();
	$(filtroSelectAnimalesSexo+filtroSelectAnimalesTipo).show()
}


$(document).ready(function(){
	$("#tipo1").on("click",function(){
		filtroSelectAnimalesTipo = ".perro";
		aplicarFiltro();
	});


	$("#tipo2").on("click",function(){
		filtroSelectAnimalesTipo = ".gato";
		aplicarFiltro();
	});

	$("#sexo1").on("click",function(){
		filtroSelectAnimalesSexo = ".macho";
		aplicarFiltro();
	});
	
	$("#sexo2").on("click",function(){
		filtroSelectAnimalesSexo = ".hembra";
		aplicarFiltro();
	});

	//igual con hembra 

});


/*
$(document).ready(function() { // valida que el telefono tenga bien el formato y sea unico
	$('#selectAnimal').keyup(function() {

		var telefono = $('#telefono').val();
		var inputTelefono = $("#telefono")[0];

		if (telefono != '') {

			$.ajax({
				url : 'http://localhost:8080/json/checktelefono/',
				data : {
					telefono : $(this).val()
				},
				type : 'GET',
				dataType : 'json',
				success : function(json) {
					if (json.result == true) { // Telefono registrado
						inputTelefono
								.setCustomValidity('Este telefono ya está registrado');

					} else if (checkTelefono(telefono) == false) { //

					} else {

					}

				},
				error : function(jqXHR, status, error) {

				},

				complete : function(jqXHR, status) {

				}
			});

		} else {
		}

	});
});
*/



