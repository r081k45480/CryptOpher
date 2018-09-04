package io.robii.cryptoffer.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity(name="crypt_offer_role")
public class CORole {

	public static final CORole USER = new CORole("USER");
	public static final CORole ADMIN = new CORole("ADMIN");
	
	@Column(unique=true)
	@Id
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	List<CryptOfferUser> users;
	
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
