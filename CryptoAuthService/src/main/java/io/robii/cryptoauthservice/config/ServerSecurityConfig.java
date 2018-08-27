package io.robii.cryptoauthservice.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import io.robii.cryptoauthservice.model.CORole;
import io.robii.cryptoauthservice.model.CryptOfferUser;
import io.robii.cryptoauthservice.model.UserDetailsAdapter;
import io.robii.cryptoauthservice.repository.CryptOfferUserRepository;

@Configuration
@EnableWebSecurity
public class ServerSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	CryptOfferUserRepository repo;
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    } 
	
	@Autowired
    public void configureGlobal(final AuthenticationManagerBuilder builder) throws Exception {
		if(repo.count()==0){
			CryptOfferUser user = new CryptOfferUser();
			user.setUsername("admin");
			user.setName("admin");
			user.setPassword(passwordEncoder().encode("admin"));
			user.setMail("admin@cryptoffer.io");
			user.setRoles(Arrays.asList(CORole.USER, CORole.ADMIN));
			repo.save(user);
		}
		builder
				.userDetailsService(username -> new UserDetailsAdapter(repo.findById(username)))
				.passwordEncoder(passwordEncoder());
            ;    
    }
	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
	
	/*
	@Override
    public void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .and()
            .csrf().disable();
    }
	*/
    
}
