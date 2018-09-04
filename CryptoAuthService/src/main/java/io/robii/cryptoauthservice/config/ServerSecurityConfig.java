package io.robii.cryptoauthservice.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

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
			CryptOfferUser user = new CryptOfferUser("admin", "admin@cryptoffer.io", "Administrator", passwordEncoder().encode("admin"), Arrays.asList(CORole.USER, CORole.ADMIN));
			repo.save(user);
			CryptOfferUser user1 = new CryptOfferUser("robii", "robii@cryptoffer.io", "Robert Sabo", passwordEncoder().encode("pass"), Arrays.asList(CORole.USER));
			repo.save(user1);
			CryptOfferUser user2 = new CryptOfferUser("jaki", "jaki@cryptoffer.io", "Jana Jaki", passwordEncoder().encode("pass"), Arrays.asList(CORole.USER));
			repo.save(user2);
			CryptOfferUser user3 = new CryptOfferUser("proka97", "proka97@cryptoffer.io", "Marko Prkoic", passwordEncoder().encode("pass"), Arrays.asList(CORole.USER));
			repo.save(user3);
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
	@Override
    public void configure(HttpSecurity web) throws Exception {
		web.authorizeRequests()
			.antMatchers("/oauth/token").permitAll();
    }
	 @Override
     public void configure(WebSecurity web) throws Exception {
       web.ignoring()
         .antMatchers(HttpMethod.OPTIONS);
     }
	 
	 
	 @Bean
    public FilterRegistrationBean corsFilterRegistrationBean() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.applyPermitDefaultValues();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(Arrays.asList("*"));
        config.setAllowedHeaders(Arrays.asList("*"));
        config.setAllowedMethods(Arrays.asList("*"));
        config.setExposedHeaders(Arrays.asList("content-length"));
        config.setMaxAge(3600L);
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean bean = new FilterRegistrationBean(new CorsFilter(source));
        bean.setOrder(0);
        return bean;
    }
    
	/*
	@Override
    public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.OPTIONS, "/oauth/token").permitAll();
        /*
            .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .anyRequest().authenticated()
                .and()
            .httpBasic()
                .and()
            .csrf().disable();* /
    }*/
	
    
}
