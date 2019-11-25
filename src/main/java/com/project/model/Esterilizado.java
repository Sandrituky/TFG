package com.project.model;

public enum Esterilizado {
	SI("Esterilizado"),
	PENDIENTE("Esterilización pendiente");
	
	private final String displayName;

	Esterilizado(String displayName) {
      this.displayName = displayName;
  }

  public String getDisplayName() {
      return displayName;
  }
}