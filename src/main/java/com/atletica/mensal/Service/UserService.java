package com.atletica.mensal.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Repository.UserRepository;

@Service
public class UserService {
	@Autowired
	private UserRepository userRepository;

	public UserEntity findByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	public UserEntity save(UserEntity user) {
		return userRepository.save(user);
	}
}
