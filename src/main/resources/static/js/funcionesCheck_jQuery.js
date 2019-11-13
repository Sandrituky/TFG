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
			var diferenciaDias = today.diff(birthday, 'days', true);
			
			if (diferencia >= 18) {
				inputFnac.setCustomValidity('');
				
			} else if(diferenciaDias<0){
				inputFnac.setCustomValidity('Aún te quedan '+Math.round(Math.abs(diferenciaDias-1))+'  días para nacer');
				
			}else{
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
			inputFnac.setCustomValidity('Debes introducir la fecha de nacimiento del animal');
		}
	});

$(document).ready(function() { // comprueba la mayoría de edad
	if ($("#raza").length) {
		$('#fnac').keyup(function() {

			var today = moment();//.format("YYYY-MM-DD")

			inputFnac = $("#fnac")[0];
			birthday = $("#fnac").val();

			var diferencia = today.diff(birthday, 'years');
			var diferenciaDias = today.diff(birthday, 'days', true);
			
			
			if (diferenciaDias >= 0 && diferencia<40) {
				inputFnac.setCustomValidity('');
				
			} else if(Math.ceil(diferenciaDias)<0){
				inputFnac.setCustomValidity('No puede registrar animales que aún no han nacido');
				
			}else{
				inputFnac.setCustomValidity('Es imposible que el animal tenga '+diferencia+ ' años');
			}
			//alert(diferenciaDias);


		});
	}
});



$(document).ready(function() { //filtrar animales en ModAnimal por tipo y sexo (radioButtons)
	$('[name=tipo], [name=sexo]').change(function() {
		

		
		$("#selectAnimal").load('/animales/checktiposexo', $('#formPreModAnimal :input[type=radio]').serialize());

		
		/*setTimeout(function(){
			var option = new Option("Seleciona animal", 0, selected="selected");
			$("#selectAnimal").append($(option));
  	}, 100);*/
		//alert($('#formPreModAnimal :input[type=radio]').serialize());

	});
});



$(document).ready(function() { 
	$( "#modificarOculto" ).hide();
	$("[name=tipo], [name=sexo], #selectAnimal").change(function() {
		$('#modificarOculto').hide('300');
		$("#selectAnimal").change(function() {
		
		inputSelectAnimal = $("#selectAnimal")[0];
		selectAnimal = $("#selectAnimal").val();
		
		if(selectAnimal>0){
		$('#modificarOculto').show('slow');
		}else if(selectAnimal==0){
			$('#modificarOculto').hide('slow');
		}
		
	
		//alert($('#formPreModAnimal :input[type=radio]').serialize());
		});
	});
});




