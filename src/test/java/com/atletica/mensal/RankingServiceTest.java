package com.atletica.mensal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.atletica.mensal.Entities.RankingEntity;
import com.atletica.mensal.Repository.RankingRepository;
import com.atletica.mensal.Service.RankingService;

public class RankingServiceTest {

	@InjectMocks
	RankingService rankingService;

	@Mock
	RankingRepository rankingRepository;

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	@DisplayName("Testar listarRanking com resultados")
	void listarRankingComResultados() {

		// Criar uma lista de RankingEntity
		List<RankingEntity> rankingList = new ArrayList<>();

		// Criar duas instâncias de RankingEntity
		RankingEntity ranking1 = new RankingEntity();
		ranking1.setPontuacaoTotal(100);

		RankingEntity ranking2 = new RankingEntity();
		ranking2.setPontuacaoTotal(90);

		// Adicionar as instâncias à lista
		rankingList.add(ranking1);
		rankingList.add(ranking2);

		// Mock do comportamento do repository
		when(rankingRepository.findAll()).thenReturn(rankingList);

		// Chamar o método a ser testado
		List<RankingEntity> resultado = rankingService.listarRanking();

		// Verificar os resultados
		assertEquals(2, resultado.size());
		assertEquals(100, resultado.get(0).getPontuacaoTotal());
		assertEquals(90, resultado.get(1).getPontuacaoTotal());
	}


	@Test
	@DisplayName("Teste listarRanking sem resultados")
	void listarRankingSemResultados() {

		// Simular a chamada para o repository retornando uma lista vazia
		when(rankingRepository.findAll()).thenReturn(new ArrayList<>());

		// Chamar o método a ser testado
		List<RankingEntity> resultado = rankingService.listarRanking();

		// Verificar se a lista retornada está vazia
		Assertions.assertTrue(resultado.isEmpty());
	}


	@Test
	@DisplayName("testa atualicarPontuacao quando a atletica existe")
	void atualizarPontuacaoAtleticaExiste() {

		RankingEntity rankingEntity = new RankingEntity();
		rankingEntity.setPontuacaoTotal(50);

		when(rankingRepository.findById(1)).thenReturn(Optional.of(rankingEntity));

		rankingService.atualizarPontuacao(1L, 10);

		Assertions.assertEquals(60, rankingEntity.getPontuacaoTotal());

	}



	@Test
	@DisplayName("testa atualizarPontuacao quando a atleticanao foi encontrada")
	void atualizarPontuacaoAtleticanaoEncontrada() {

		when(rankingRepository.findById(1)).thenReturn(Optional.empty());

		rankingService.atualizarPontuacao(1L, 10);

		// verify(rankingRepository, never()).save(any(RankingEntity.class));

	}

}
