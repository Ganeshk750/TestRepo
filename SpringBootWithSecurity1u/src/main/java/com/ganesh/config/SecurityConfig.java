package com.ganesh.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	//private DataSource dSource;
	private UserDetailsService userDetails;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetails).passwordEncoder(passwordEncoder);
	
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		//For H2-console
		http.authorizeRequests().antMatchers("/").permitAll().and()
            .authorizeRequests().antMatchers("/h2-console/**").permitAll();
       // http.csrf().disable();
       // http.headers().frameOptions().disable();
        
        
        
		
		//declares which page(URL) will have what access type
		http.authorizeRequests()
		.antMatchers("/home", "/register", "/saveUser").permitAll()
		.antMatchers("/welcome").authenticated()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.antMatchers("/emp").hasAuthority("EMPLOYEE")
		.antMatchers("/mgr").hasAuthority("MANAGER")
		.antMatchers("/hr").hasAuthority("HR")
		.antMatchers("/common").hasAnyAuthority("EMPLOYEE,MANAGER,ADMIN")
		
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
