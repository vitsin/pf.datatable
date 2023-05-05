package org.primefaces.test;

import java.io.Serializable;

public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;
    private String name;
    private String code;
    
	public Country(Integer id, String name, String code) {
		this.id = id;
		this.name = name;
		this.code = code;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}


}
