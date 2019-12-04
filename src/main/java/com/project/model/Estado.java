package com.project.model;

public enum Estado {
	EN_ADOPCION("En adopción","Cancelar adopción"),
	RESERVADO("Reservado","Reservar"),
	ADOPTADO("Adoptado","Aceptar adopción");
	
	
  private final String displayName;
  private final String accion;

  Estado(String displayName, String accion) {
      this.displayName = displayName;
      this.accion = accion;
  }

  public String getDisplayName() {
      return displayName;
  }
  
  public String getAccion() {
    return accion;
}

	
}