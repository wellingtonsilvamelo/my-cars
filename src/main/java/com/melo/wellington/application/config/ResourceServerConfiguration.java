package com.melo.wellington.application.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.web.access.AccessDeniedHandler;

import com.melo.wellington.application.exception.RestExceptionHandler;

@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter{
	
	@Value("${security.oauth2.client.resource-ids}")
    private String RESOURCE_ID;
	
	@Bean
	public AccessDeniedHandler accessDeniedHandler(){
	    return new RestExceptionHandler();
	}

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.resourceId(RESOURCE_ID);
    }
    
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/api/users/**").permitAll()
                .antMatchers("/api/signin").permitAll() 
                .anyRequest()
                .authenticated();
    }

}
