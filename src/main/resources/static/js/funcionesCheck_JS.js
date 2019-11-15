function checkDni(dni){
  var numero;
  var letra;
  var letras;
  var expresion_regular_dni;
  var inputDNI = document.getElementById("dni");
  
  expresion_regular_dni = /^\d{8}[a-zA-Z]$/;
  
  if(expresion_regular_dni.test (dni) == true){
     numero = dni.substr(0,dni.length-1);
     letra = dni.substr(dni.length-1,1);
     letra=letra.toUpperCase();
     numero = numero % 23;
     letras='TRWAGMYFPDXBNJZSQVHLCKET';
     letras=letras.substring(numero,numero+1);
     if (letras!=letra) {
    	 inputDNI.setCustomValidity('La letra no corresponde con el DNI');      
     }else{
    	 inputDNI.setCustomValidity('');
       return true;
     }
  }else{
  	inputDNI.setCustomValidity('DNI no válido');
  }
  return false;
}

function checkEmail(email){
	
	var regex =  /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  var inputEmail = document.getElementById("email");
  if(regex.test (email) == true){
  	inputEmail.setCustomValidity('');
  	setTimeout(function(){
  		inputEmail.classList.remove("loading");
  	}, 1000);
  	


  }else{
  	inputEmail.setCustomValidity('Email no válido');
  	inputEmail.classList.add("loading");
  }
  
}

function checkTelefono(telefono){
	var regex =  /^(\+34|0034|34)?[6|7|9][0-9]{8}$/;
  var inputTelefono = document.getElementById("telefono");
  
  if(regex.test (telefono) == true){
  	inputTelefono.setCustomValidity('');
  	// document.getElementById("status").innerHTML="";

  }else{
  	inputTelefono.setCustomValidity('Teléfono no válido');
  }
  
}
