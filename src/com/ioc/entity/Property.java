package com.ioc.entity;

/**
 * Property类用于存储bean下的每一个属性结构，主要有name，value，ref三个属性.
 * @author zt
 */
public class Property {
	private String name;
	private String value;
	private String ref;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getValue() {
		return value;
	}
	
	public void setValue(String value) {
		this.value = value;
	}
	
	public String getRef() {
		return ref;
	}
	
	public void setRef(String ref) {
		this.ref = ref;
	}
	
}
