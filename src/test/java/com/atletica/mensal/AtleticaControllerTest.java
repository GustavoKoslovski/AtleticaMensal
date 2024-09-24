package com.atletica.mensal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.atletica.mensal.Controller.AtleticaController;
import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Service.AtleticaService;

@SpringBootTest
public class AtleticaControllerTest {

	@Mock
	private AtleticaService atleticaService;

	@InjectMocks
	private AtleticaController atleticaController;
	
	   public AtleticaControllerTest() {
	        MockitoAnnotations.openMocks(this); // Inicializa mocks
	    }


	@Test
	void testListarTodasAtleticas() throws Exception {
		
		  // Criação da entidade de exemplo
        AtleticaEntity atletica = new AtleticaEntity();
        atletica.setNome("Atlética Ursao");


        when(atleticaService.listarTodasAtleticas()).thenReturn(List.of(atletica));

   
        List<AtleticaEntity> resultado = atleticaController.listarTodasAtleticas();

        // Valida 
        assertEquals(1, resultado.size());
        assertEquals("Atlética Ursao", resultado.get(0).getNome());
    }


	@Test
	void testCriarAtletica() throws Exception {
		
		// Criação da entidade a ser retornada no teste
        AtleticaEntity atletica = new AtleticaEntity();
        atletica.setNome("Atlética ABC");

        // serviço ao salvar uma atlética
        when(atleticaService.salvarAtletica(any(AtleticaEntity.class))).thenReturn(atletica);

        // Cria uma entidade de entrada
        AtleticaEntity novaAtletica = new AtleticaEntity();
        novaAtletica.setNome("Atlética ABC");
        novaAtletica.setUniversidade("Universidade XYZ");

        //  controller diretamente e verifica o retorno
        AtleticaEntity resultado = atleticaController.criarAtletica(novaAtletica);

        assertEquals("Atlética ABC", resultado.getNome());
    }
	

	@Test
	void testDeletarAtletica() throws Exception {
		
		//deletar uma atlética
        doNothing().when(atleticaService).deletarAtletica(1L);
        atleticaController.deletarAtletica(1L);

        // Verifica se o serviço foi chamado uma vez com o ID correto
        verify(atleticaService, times(1)).deletarAtletica(1L);
    }
}
