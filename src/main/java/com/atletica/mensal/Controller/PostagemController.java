package com.atletica.mensal.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.atletica.mensal.Entities.PostagemEntity;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Service.PostagemService;
import com.atletica.mensal.Service.UserService;

@RestController
@RequestMapping("/postagens")
public class PostagemController {

	@Autowired
	private PostagemService postagemService;

	@Autowired
	private UserService userService;

	@PostMapping("/criar")
	public PostagemEntity criarPostagem(@RequestBody PostagemEntity postagem, @RequestParam String email) {
		UserEntity user = userService.findByEmail(email);
		return postagemService.salvarPostagem(postagem, user);
	}

	@GetMapping
	public List<PostagemEntity> listarPostagensPorAtletica(@PathVariable long atletica_id) {
		return postagemService.listarPostagensPorAtletica(atletica_id);
	}
}
