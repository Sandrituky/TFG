//___________USUARIOS________________________

//REGISTRAR USUARIO: valida que el correo sea unico y tenga el formato correcto
$(document).ready(function() {
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


//REGISTRAR USUARIO: valida que el DNI sea unico y valido
$(document).ready(function() { 
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

//REGISTRAR USUARIO: valida que el telefono sea unico y tenga el formato correcto
$(document).ready(function() {
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

//REGISTRAR USUARIO: Quita la fecha por defecto para que aparezca vacía
$(document).ready(function() {
	if ($("#dni").length) { // comprueba que el elemento existe
		inputFnac = $('#fnac')[0];
		fnac = $('#fnac').val("");

		inputFnac.setCustomValidity('Debes introducir tu fecha de nacimiento');

	}
});

//REGISTRAR USUARIO: Comprueba la mayoría de edad
$(document).ready(function() { 
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

//ALTA ANIMAL: Pone todos los radioButton del formulario en unchecked (PUEDE QUE YA NO SEA NECESARIA ESTA FUNCION)
$(document).ready(function() { 
	if ($("#raza").length) { // comprueba que el elemento existe

		$('input[type="radio"]').prop('checked', false);
	}
});

//ALTA ANIMAL: Quita la fecha por defecto para que aparezca vacía
$(document).ready(function() {
		if ($("#raza").length) { // comprueba que el elemento existe
			
			inputFnac = $('#fnac')[0];
			fnac = $('#fnac').val("");
			inputFnac.setCustomValidity('Debes introducir la fecha de nacimiento del animal');
		}
	});

//ALTA ANIMAL: Impide registrar animales que no hayan nacido o tengan mas de 40 años
$(document).ready(function() { 
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


//MODIFICAR ANIMALES: filtra la busqueda por tipo y sexo para llenar el Select
$(document).ready(function() { 
	$("#selectAnimal").load('/animales/checktiposexo', $('[name=selectTipo], [name=selectSexo]').serialize());
	$('[name=selectTipo], [name=selectSexo]').change(function() {
		
		$("#selectAnimal").load('/animales/checktiposexo', $('[name=selectTipo], [name=selectSexo]').serialize());
		//alert($('[name=selectTipo], [name=selectSexo]').serialize());
		
		

	});
});


//MODIFICAR ANIMALES: Carga la información del select en el formulario de modificar
$(document).ready(function() {
	$("#divFormModificar").hide();
	
	$("[name=selectTipo], [name=selectSexo]").change(function() {
		$('#divFormModificar').hide('300');
		
	});
	
	$("#selectAnimal").change(function() {
		$("#divFormModificar").hide();
		inputSelectAnimal = $("#selectAnimal")[0];
		selectAnimal = $("#selectAnimal").val();
		
		if (selectAnimal > 0) {
			$('#divFormModificar').show('300');
			
			$('#divFormModificar').load('/animales/animalid', $("#selectAnimal").serialize(), loadFormModificar());

		} else if (selectAnimal == 0) {
			$('#divFormModificar').hide('slow');
		}

	});
});


//Funcion para subir la imagen y mostrar preview en ALTA ANIMAL
$(document).ready(function(){
  $("#uploadFile").on("change", function(){
      var files = !!this.files ? this.files : [];
      if (!files.length || !window.FileReader){ // no file selected, or no FileReader support
      	$("#imagePreview").css("background-image", "url('http://via.placeholder.com/350x150')");
      	
      }
      

      if (/^image/.test( files[0].type)){ // only image file
          var reader = new FileReader(); // instance of the FileReader
          reader.readAsDataURL(files[0]); // read the local file

          reader.onloadend = function(){ // set image data as background of div
              $("#imagePreview").css("background-image", "url("+this.result+")");
              
          }
      }
  });


$('#imagePreview').click(function(){
$('#uploadFile').click();
});
});


//Funcion para subir la imagen y mostrar preview en MODIFICAR ANIMAL
//es distinta a la de ALTA porque el codigo debe cargar despues de cargar el th:fragment
function loadFormModificar() {
	
	var checkExist = setInterval(function() { // va a comprobar si existe imagePreview cada 100ms hasta que exista.
		
		if ($("#imagePreview").length) {
			
			$("#uploadFile").on("change",function() {
				
				$("#uploadFile").prop('required',true);
				
						var files = !!this.files ? this.files : [];
						
						if (!files.length || !window.FileReader) { // no file selected, or no FileReader support
							
							
							$("#imagePreview").css("background-image","url('http://via.placeholder.com/350x150')");

						}

						if (/^image/.test(files[0].type)) { // only image file
							var reader = new FileReader(); // instance of the FileReader
							reader.readAsDataURL(files[0]); // read the local file

							reader.onloadend = function() { // set image data as background of div
								$("#imagePreview").css("background-image",
										"url(" + this.result + ")");

							}
						}

					});

			$('#imagePreview').click(function() {
				$('#uploadFile').click();
			});
			clearInterval(checkExist);
		}
	}, 100);
}



