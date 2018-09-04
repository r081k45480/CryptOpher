package io.robii.cryptoffer.config;

import java.util.Arrays;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CORSConfig extends WebSecurityConfigurerAdapter {
	
	@Override
    public void configure(HttpSecurity web) throws Exception {
		web.authorizeRequests().antMatchers("/**").permitAll();
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
}
