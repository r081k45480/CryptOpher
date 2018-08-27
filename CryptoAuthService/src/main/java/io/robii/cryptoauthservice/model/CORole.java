package io.robii.cryptoauthservice.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity(name="crypt_offer_role")
public class CORole {

	public static final CORole USER = new CORole("USER");
	public static final CORole ADMIN = new CORole("ADMIN");
	
	@Column(unique=true)
	@Id
	private String name;
	
	public CORole(){}
	
	public CORole(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		
		this.name = name;
	}
	@Override
	public String toString() {
		return getName().toUpperCase();
	}
	
	
	
}
