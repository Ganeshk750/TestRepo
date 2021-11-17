package com.ganesh.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private DataSource dSource;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		.dataSource(dSource)     //creates database connection
		.usersByUsernameQuery("select user_name,user_pwd,user_enabled from user where user_name=?")
		.authoritiesByUsernameQuery("select user_name,user_role from user where user_name=?")
		.passwordEncoder(passwordEncoder);
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//For H2-console
		http.authorizeRequests().antMatchers("/").permitAll().and()
            .authorizeRequests().antMatchers("/console/**").permitAll();
        http.csrf().disable();
        http.headers().frameOptions().disable();
        
        
        
		
		//declares which page(URL) will have what access type
		http.authorizeRequests()
		.antMatchers("/home").permitAll()
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/emp").hasAuthority("EMPLOYEE")
		.antMatchers("/mgr").hasAuthority("MANAGER")
		.antMatchers("/common").hasAnyAuthority("EMPLOYEE", "MANAGER")
		
		//Any other URLs which are not configure in above antMatchers
		// generally declared authenticated() in real time
		.anyRequest().authenticated()
		
		//Login Form Details
		.and()
		.formLogin()
		.defaultSuccessUrl("/welcome", true)
		
		//Logout From Details
		.and()
		.logout()
		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		
		//Exception Details
		.and()
		.exceptionHandling()
		.accessDeniedPage("/accessDenied");
	}

}
