package com.atletica.mensal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.atletica.mensal.Entities.RankingEntity;
import com.atletica.mensal.Repository.RankingRepository;

@Service
public class RankingService {

	@Autowired
	private RankingRepository rankingRepository;
	
	public List<RankingEntity> listarRankings (){
		
		return rankingRepository.findAll(Sort.by(Sort.Direction.DESC,"pontuacao"));
	}
	
	public void atualizarPontuacao (Long atletica_id, int pontos) {
		Optional<RankingEntity> rankingOpt = rankingRepository.findById(atletica_id);
		if (rankingOpt.isPresent()) {
			RankingEntity ranking = rankingOpt.get();
			ranking.setPontuacaoTotal(ranking.getPontuacaoTotal() + pontos);
			rankingRepository.save(ranking);		
		} else {
			System.out.println("atlética não encontrada no ranking");
		}
		
	}
	
	public RankingEntity saveRanking(RankingEntity rankingEntity) {
		
		return rankingRepository.save(rankingEntity);
	}
	
	public List<RankingEntity> listarRanking(){
		
		return rankingRepository.findAll();
	}
	
	public RankingEntity findById(Integer id) {
		
		try {
			return rankingRepository.findById(id).orElseThrow();
		} catch (Exception e) {
			System.out.println(e.getCause());
			return new RankingEntity();
		}
	}
	
	// cod pra teste
	//rankingService.atualizarPontuacao(1L, 50); 
}
