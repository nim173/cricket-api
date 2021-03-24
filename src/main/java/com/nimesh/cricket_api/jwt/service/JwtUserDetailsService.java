package com.nimesh.cricket_api.jwt.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.nimesh.cricket_api.jwt.model.User;
import com.nimesh.cricket_api.jwt.model.UserDTO;
import com.nimesh.cricket_api.jwt.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private PasswordEncoder bcryptEncoder;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with username: " + username);
		} else {
			List<SimpleGrantedAuthority> roles=null;
			if (user.isAdmin()) {
				roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
			} else {
				roles = Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
			}
			return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
		}
	}

	public User save(UserDTO userDTO) {
		User newUser = new User();
		newUser.setUsername(userDTO.getUsername());
		newUser.setPassword(bcryptEncoder.encode(userDTO.getPassword()));
		return userRepository.save(newUser);
	}

	public boolean userExists(String username) {
		if (userRepository.findByUsername(username) == null) {
			return false;
		} else {
			return true;
		}
	}
}