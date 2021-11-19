package com.ganesh.service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.ganesh.model.User;
import com.ganesh.repository.UserRepository;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	
	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder bCryptEncoder;
	

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<User> optUser = userRepo.findByUsername(username);
		org.springframework.security.core.userdetails.User springUser=null;
		if(optUser.isEmpty()) {
			throw new UsernameNotFoundException("User with username: " +username +" not found");
		}else {
			User user = optUser.get(); 
			Set<String> roles = user.getRoles();
			Set<GrantedAuthority> ga = new HashSet<>();
			for(String role: roles) {
				ga.add(new SimpleGrantedAuthority(role));
			}
			
			springUser = new org.springframework.security.core.userdetails.User(username, user.getPassword(), ga);
			
		}
		return springUser;
	}

	@Override
	public Integer saveUser(User user) {
		// Encode Password before Saving to DB
		user.setPassword(bCryptEncoder.encode(user.getPassword()));
		return userRepo.save(user).getId();
	}

	@Override
	public Optional<User> findByUsername(String username) {
		return userRepo.findByUsername(username);
	}

}
