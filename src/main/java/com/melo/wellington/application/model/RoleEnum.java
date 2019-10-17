package com.melo.wellington.application.model;

public enum RoleEnum {

    ROLE_USER(2L),
    ROLE_ADMIN(1L);
	
	private Long code;

    private RoleEnum(Long code) {
		this.code = code;
	}

	public static String[] names() {
    	String[] names = new String[values().length];
    	for(int index = 0; index < values().length; index++) {
    		names[index] = values()[index].name();
    	}
    	
    	return names;
    }

	public Long getCode() {
		return code;
	}	
	
}