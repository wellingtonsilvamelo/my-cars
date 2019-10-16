package com.melo.wellington.application.model;

public enum RoleEnum {

    ROLE_USER,
    ROLE_ADMIN;

    public static String[] names() {
    	String[] names = new String[values().length];
    	for(int index = 0; index < values().length; index++) {
    		names[index] = values()[index].name();
    	}
    	
    	return names;
    }
}