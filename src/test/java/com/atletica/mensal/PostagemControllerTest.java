package com.atletica.mensal;
import com.atletica.mensal.Controller.PostagemController;
import com.atletica.mensal.Entities.PostagemEntity;
import com.atletica.mensal.Entities.UserEntity;
import com.atletica.mensal.Service.PostagemService;
import com.atletica.mensal.Service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
public class PostagemControllerTest {

    @Mock
    private PostagemService postagemService;

    @Mock
    private UserService userService;  // Adicione o mock para o UserService

    @InjectMocks
    private PostagemController postagemController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); // Inicializa mocks
    }

    @Test
    void testCriarPostagem() throws Exception {
        // Criar um usuário de teste
        UserEntity user = new UserEntity();
        user.setNome("Atletica Ursao");
        user.setEmail("atletica@gmail.com");

        // Simular o comportamento do UserService
        when(userService.findByEmail("atletica@gmail.com")).thenReturn(user);

        // Criar uma postagem de teste
        PostagemEntity post = new PostagemEntity();
        post.setImagem("Nova Festa");
        post.setUser(user);

        // Simular o comportamento do PostagemService
        when(postagemService.salvarPostagem(any(PostagemEntity.class), any(UserEntity.class))).thenReturn(post);

        // Nova postagem para ser criada
        PostagemEntity novaPostagem = new PostagemEntity();
        novaPostagem.setImagem("Nova Festa");

        // Chamar o controller para criar a postagem
        PostagemEntity resultado = postagemController.criarPostagem(novaPostagem, "atletica@gmail.com");

        // Verificar o resultado
        assertEquals("Nova Festa", resultado.getImagem());
        assertEquals("Atletica Ursao", resultado.getUser().getNome());
    }

    @Test
    public void testListarPostagensPorAtletica() throws Exception {
        // Criação das entidades de postagens com imagem
        PostagemEntity postagem1 = new PostagemEntity();
        postagem1.setImagem("Festa 1");

        PostagemEntity postagem2 = new PostagemEntity();
        postagem2.setImagem("Festa 2");

        when(postagemService.listarPostagensPorAtletica(1L)).thenReturn(List.of(postagem1, postagem2));

        List<PostagemEntity> resultado = postagemController.listarPostagensPorAtletica(1L);

        // Verifica o conteúdo da imagem nas postagens retornadas
        assertEquals(2, resultado.size());
        assertEquals("Festa 1", resultado.get(0).getImagem());
        assertEquals("Festa 2", resultado.get(1).getImagem());
    }
}
