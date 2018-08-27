package io.robii.cryptoauthservice.model;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsAdapter implements UserDetails {

	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	
	private boolean found = true;
	
	public UserDetailsAdapter(Optional<CryptOfferUser> oUser) {
		if(oUser.isPresent()){
			CryptOfferUser user = oUser.get();
			this.username = user.getUsername(); 
			this.password = user.getPassword();
			authorities = user.getRoles().stream().map( role -> new SimpleGrantedAuthority(role.toString())).collect(Collectors.toList());
		} else 
			found = false;
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return authorities;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return found;
	}

}
