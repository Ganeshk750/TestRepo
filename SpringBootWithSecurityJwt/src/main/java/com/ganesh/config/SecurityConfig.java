package com.ganesh.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.ganesh.filter.SecurityFilter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UnAuthorizedUserAuthenticationEntryPoint authEntryPoin; 
	
	@Autowired
	private SecurityFilter securityFilter;
	
	
	// Required in case of Stateless Authentication
	@Override
	@Bean
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
//		 auth.parentAuthenticationManager(authenticationManagerBean())
//	        .userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable() //Disabled CSRF as not using form based login
		.authorizeRequests()
		.antMatchers("/user/saveUser", "/user/loginUser").permitAll()
		.anyRequest().authenticated()
		.and()
		.exceptionHandling()
		.authenticationEntryPoint(authEntryPoin)
		.and()
		.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		// To Verify user from second request onwards ...
		.and()
		.addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class);
	}

}
