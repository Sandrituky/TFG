package com.project.model;

public enum Esterilizado {
	SI("Esterilizado", "Si"),
	PENDIENTE("Esterilización pendiente", "Pendiente");
	
	private final String displayName;
  private final String displayName2;

	Esterilizado(String displayName, String displayName2) {
      this.displayName = displayName;
      this.displayName2 = displayName2;
  }

  public String getDisplayName() {
      return displayName;
  }
  
  public String getDisplayName2() {
    return displayName2;
}
}