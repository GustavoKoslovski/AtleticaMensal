package com.atletica.mensal.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/registrar")
	public UserEntity registrar(@RequestBody UserEntity user) {
		return userService.save(user);
	}

	@GetMapping("/{email}")
	public UserEntity buscarPorEmail(@PathVariable String email) {
		return userService.findByEmail(email);
	}
}
