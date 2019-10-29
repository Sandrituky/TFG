$(document).ready(function() {
	$('#inputEmail').keyup(function() {
		
		
		var email = $('#inputEmail').val();
		var inputEmail = $("#inputEmail")[0];
		
		
		$('#status').html('<img id="loading" src="/imagenes/loading.gif">');
		
		if(email!=''){
			
			   //var emailPattern = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
			   //return emailPattern.test(email);
			
			$.ajax({
			    url: 'http://localhost:8080/json/checkemail/', 
			    data: { email : $(this).val() },
			    type : 'GET',
			    dataType : 'json',
			    success : function(json) {
			    	if(json.result == true && AQUI FALTA ALGO)==false ){
			    		inputEmail.setCustomValidity('¡El correo ya existe!');
			    		$('#status').html('');
			    	}else{
			    		
			    		inputEmail.setCustomValidity('');
			    	}
			    	
			    	
			    },
			    error : function(jqXHR, status, error) {
			    	$('#status').html('error fatal');
			    },
			 
			    complete : function(jqXHR, status) {
			    	
			    }
			});
			
			
			
		}else{
			$('#status').html('');
		}
		
	});
});