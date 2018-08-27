package io.robii.cryptoffer.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter{

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/**").authenticated()
			.antMatchers("/public").permitAll();
	}
	
	@Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
         resources.resourceId("oauth2-resurce");
    }
	
	@Primary
	@Bean
	public RemoteTokenServices tokenService(){
		RemoteTokenServices tokenService = new RemoteTokenServices();
	    tokenService.setCheckTokenEndpointUrl(
	      "http://localhost:8044/api/oauth/check_token");
	    tokenService.setClientId("trusted-auth-client");
	    tokenService.setClientSecret("crypto-secret");
	    return tokenService;
	}
}
