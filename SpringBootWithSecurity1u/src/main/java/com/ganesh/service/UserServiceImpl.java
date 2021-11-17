package com.ganesh.service;

import java.util.HashSet;
import java.util.List;
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
	private BCryptPasswordEncoder pEncoder;

	@Override
	public Integer saveUser(User user) {
		String passwd = user.getPassword();
		String encodePasswod = pEncoder.encode(passwd);
		user.setPassword(encodePasswod);
		user = userRepo.save(user);
		return user.getId();
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Optional<User> uOpt = userRepo.findUserByEmail(email);
		org.springframework.security.core.userdetails.User springUser=null; 
		
		if(uOpt.isEmpty()) {
			throw new UsernameNotFoundException("User with email: "+ email + "not found");
		}else {
			User user = uOpt.get();
			List<String> roles = user.getRoles();
			Set<GrantedAuthority> ga = new HashSet<>();
			for(String role: roles) {
				ga.add(new SimpleGrantedAuthority(role));
			}
			
			springUser = new org.springframework.security.core.userdetails.User
					(email, user.getPassword(), ga);
		}
		
		return springUser;
	}

}
























