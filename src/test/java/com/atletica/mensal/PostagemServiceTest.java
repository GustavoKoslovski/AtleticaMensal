package com.atletica.mensal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Entities.PostagemEntity;
import com.atletica.mensal.Repository.AtleticaRepository;
import com.atletica.mensal.Repository.PostagemRepository;
import com.atletica.mensal.Service.AtleticaService;
import com.atletica.mensal.Service.PostagemService;

@SpringBootTest
public class PostagemServiceTest {

	@Mock
	PostagemRepository postagemRepository;

	@Mock
	AtleticaRepository atleticaRepository;

	@InjectMocks
	private PostagemService postagemService;

	public PostagemServiceTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCriarPostagemComPermissao() {
		AtleticaEntity atleticaEntity = new AtleticaEntity();
		PostagemEntity postagem = new PostagemEntity();
		postagem.setImagem("Programação da festa");

		AtleticaEntity atletica = new AtleticaEntity();
		atletica.setId(1L);

		// Simulações
		when(atleticaRepository.findById(1L)).thenReturn(Optional.of(atletica));
		when(postagemRepository.save(any(PostagemEntity.class))).thenReturn(postagem);


		PostagemEntity resultado = postagemService.criarPostagem(1L, postagem, 1L);

		// Verificações
		assertNotNull(resultado);
		assertEquals("Programação da festa", resultado.getImagem());
		verify(postagemRepository, times(1)).save(postagem);
	}

	@Test
	void testCriarPostagemSemPermissao() {

		PostagemService postagemService = new PostagemService();

		AtleticaEntity atleticaEntity = new AtleticaEntity();
		atleticaEntity.setId(2L); // Simula que o ID do usuário autorizado é diferente

		when(atleticaRepository.findById(1L)).thenReturn(Optional.of(atleticaEntity));

		// Execução e Verificação
		Exception exception = assertThrows(RuntimeException.class, () -> {
			// Aqui você deve chamar o método correto do serviço de postagem
			postagemService.criarPostagem(1L, new PostagemEntity(), 1L); // nova postagem
		});

		assertEquals("Usuário não autorizado a postar", exception.getMessage());

		// Verifica que nenhuma postagem foi salva no repositório
		verify(postagemRepository, never()).save(any(PostagemEntity.class));
	}
}
