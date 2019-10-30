function checkDni(dni){
  var numero;
  var let;
  var letra;
  var expresion_regular_dni;
  var inputDNI = document.getElementById("inputDNI");
  
  expresion_regular_dni = /^\d{8}[a-zA-Z]$/;
  
  if(expresion_regular_dni.test (dni) == true){
     numero = dni.substr(0,dni.length-1);
     let = dni.substr(dni.length-1,1);
     let=let.toUpperCase();
     numero = numero % 23;
     letra='TRWAGMYFPDXBNJZSQVHLCKET';
     letra=letra.substring(numero,numero+1);
     if (letra!=let) {
    	 inputDNI.setCustomValidity('La letra no corresponde con el DNI');      
     }else{
    	 inputEmail.style.backgroundImage ="none";
       return true;
     }
  }else{
  	inputDNI.setCustomValidity('DNI no válido');
  }
  return false;
}

function checkEmail(email){
	
	var regex =  /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
  var inputEmail = document.getElementById("inputEmail");
  
  if(regex.test (email) == true){
  	inputEmail.setCustomValidity('');
  	inputEmail.classList.remove("loading");


  }else{
  	inputEmail.setCustomValidity('DNI no válido');
  	inputEmail.classList.remove("loading");
  }
  
}

function checkTelefono(telefono){
	var regex =  /^(\+34|0034|34)?[6|7|9][0-9]{8}$/;
  var inputTelefono = document.getElementById("inputTelefono");
  
  if(regex.test (telefono) == true){
  	inputTelefono.setCustomValidity('');
  	// document.getElementById("status").innerHTML="";

  }else{
  	inputTelefono.setCustomValidity('Teléfono no válido');
  }
  
}
