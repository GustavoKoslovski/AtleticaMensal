package com.atletica.mensal.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Entities.PostagemEntity;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Repository.AtleticaRepository;
import com.atletica.mensal.Repository.PostagemRepository;

@Service
public class PostagemService {

	@Autowired
	private PostagemRepository postagemRepository;
	@Autowired
	private AtleticaRepository atleticaRepository;

	public List<PostagemEntity> listarPostagens() {
		return postagemRepository.findAll();
	}

	public PostagemEntity criarPostagem(long atletica_id, PostagemEntity postagem, long user_id)   {
        AtleticaEntity atletica = atleticaRepository.findById(atletica_id)
                .orElseThrow(() -> new RuntimeException("Atlética não encontrada"));

        // Verificar se o usuário tem permissão para postar 
        if (!atletica.getDonoId().equals(user_id)) {
            throw new RuntimeException("Usuário não autorizado a postar");
        }

        // Associar a postagem à atlética
 
		postagem.setAtletica(atletica);
		return postagemRepository.save(postagem);
           }

	public List<PostagemEntity> listarPostagensPorAtletica(long atletica_id) {
		return postagemRepository.findByAtleticaId(atletica_id);
	}

	public PostagemEntity salvarPostagem(PostagemEntity postagem, UserEntity user) {
		 postagem.setUser(user);
		return postagemRepository.save(postagem);
	}

}
