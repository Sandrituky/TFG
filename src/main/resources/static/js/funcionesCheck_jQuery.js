$(document).ready(function() { //valida que el correo tenga bien el formato y sea unico
	$('#inputEmail').keyup(function() {
		
		
		var email = $('#inputEmail').val();
		var inputEmail = $("#inputEmail")[0];
		
		
		//inputEmail.style.backgroundImage = "url('/imagenes/loading.gif')";

		
		
		if(email!=''){
			
			   //var pattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
			   //var hola = pattern.test(email);
			$(inputEmail).addClass("loading");

			
			$.ajax({
			    url: 'http://localhost:8080/json/checkemail/', 
			    data: { email : $(this).val() },
			    type : 'GET',
			    dataType : 'json',
			    success : function(json) {
			    	if(json.result==true){
			    		inputEmail.setCustomValidity('Este correo ya está registrado');
			    		$(inputEmail).removeClass("loading");
			    		
			    		
			    		}else if(checkEmail(email)==false){
			    			$(inputEmail).addClass("loading");

			    	}else{
			    		inputEmail.classList.add("loading");

			    	}
			    
			    },
			    error : function(jqXHR, status, error) {
			    	
			    },
			 
			    complete : function(jqXHR, status) {
			    	
			    }
			});
			
			
			
		}else{


		}
		
	});
});




$(document).ready(function() { //valida que el DNI tenga bien el formato y sea unico
	$('#inputDNI').keyup(function() {
		
		var dni = $('#inputDNI').val();
		var inputDNI = $("#inputDNI")[0];
		
		$('#status').html('<img id="loading" src="/imagenes/loading.gif">');
		
		if(dni!=''){
				
			$.ajax({
			    url: 'http://localhost:8080/json/checkdni/', 
			    data: { dni : $(this).val() },
			    type : 'GET',
			    dataType : 'json',
			    success : function(json) {
			    	if(json.result==true){ //DNI registrado
			    		inputDNI.setCustomValidity('Este DNI ya está registrado');
			    		
			    		}else if(checkDni(dni)==false){ //
			    			
			    	}else{	
			    		
			    	}
			    	
			    	
			    },
			    error : function(jqXHR, status, error) {
			    	
			    },
			 
			    complete : function(jqXHR, status) {
			    	
			    }
			});

		}else{
			//$('#status').html('');
		}
		
	});
});

$(document).ready(function() { //valida que el telefono tenga bien el formato y sea unico
	$('#inputTelefono').keyup(function() {
		
		var telefono = $('#inputTelefono').val();
		var inputTelefono = $("#inputTelefono")[0];
		
		$('#status').html('<img id="loading" src="/imagenes/loading.gif">');
		
		if(telefono!=''){
				
			$.ajax({
			    url: 'http://localhost:8080/json/checktelefono/', 
			    data: { telefono : $(this).val() },
			    type : 'GET',
			    dataType : 'json',
			    success : function(json) {
			    	if(json.result==true){ //Telefono registrado
			    		inputTelefono.setCustomValidity('Este telefono ya está registrado');
			    		
			    		}else if(checkTelefono(telefono)==false){ //
			    			
			    	}else{	
			    		
			    	}
			    	
			    	
			    },
			    error : function(jqXHR, status, error) {
			    	
			    },
			 
			    complete : function(jqXHR, status) {
			    	
			    }
			});

		}else{
			//$('#status').html('');
		}
		
	});
});



