package com.atletica.mensal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.atletica.mensal.Controller.RankingController;
import com.atletica.mensal.Repository.RankingRepository;
import com.atletica.mensal.Repository.AtleticaRepository;
import com.atletica.mensal.Entities.RankingEntity;
import com.atletica.mensal.Entities.AtleticaEntity;

import java.util.Optional;

@SpringBootTest
public class RankingControllerTest {

	@Autowired
	RankingController rankingController;

	@MockBean
	RankingRepository rankingRepository;

	@MockBean
	AtleticaRepository atleticaRepository;

	@BeforeEach
	void setup() {
		AtleticaEntity atletica = new AtleticaEntity();
		atletica.setId(1L);
		atletica.setNome("Teste");

		RankingEntity ranking = new RankingEntity();
		ranking.setPontuacaoTotal(25);
		ranking.setAtletica(atletica);  // Associar a Atletica ao Ranking

		Mockito.when(atleticaRepository.findById(1L)).thenReturn(Optional.of(atletica));
		Mockito.when(rankingRepository.findById(1L)).thenReturn(Optional.of(ranking));
		Mockito.when(rankingRepository.save(Mockito.any(RankingEntity.class))).thenReturn(ranking);
	}

	@Test
	@DisplayName("Testa a atualização da pontuação")
	void pontMethodTest() {
		ResponseEntity<Integer> response = rankingController.atualizarPontuacao(1L, 2);

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(27, response.getBody());  // Ou o valor esperado após a atualização
	}
}
