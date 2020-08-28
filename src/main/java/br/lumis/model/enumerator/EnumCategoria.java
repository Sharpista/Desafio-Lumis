package br.lumis.model.enumerator;

public enum EnumCategoria {

	CATEGORIA1("Categoria 1"),
	CATEGORIA_2("Categoria 2"),
	CATEGORIA_3("Categoria 3"),
	CATEGORIA_4("Categoria 4");
	
	 private final String displayValue;
	    
	    private EnumCategoria(String displayValue) {
	        this.displayValue = displayValue;
	    }
	    
	    public String getDisplayValue() {
	        return displayValue;
	    }
}
