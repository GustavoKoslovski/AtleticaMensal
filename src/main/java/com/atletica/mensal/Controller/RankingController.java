package com.atletica.mensal.Controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atletica.mensal.Entities.RankingEntity;
import com.atletica.mensal.Repository.RankingRepository;
import com.atletica.mensal.Service.RankingService;

@RestController
@RequestMapping
public class RankingController {

	@Autowired
	private RankingService rankingService;
	
	@Autowired
	private RankingRepository rankingRepository;
	
	/*public void ResponseEntity<Integer> atualizarPontuacao (@PathVariable Integer atletica_id,@PathVariable int pontos) {
		Optional<RankingEntity> rankingOpt = rankingRepository.findByAtleticaId(atletica_id);
				
		if(rankingOpt.isPresent()) {
			RankingEntity ranking = rankingOpt.get();
			
			ranking.setPontuacaoTotal(ranking.getPontuacaoTotal() + pontos);
			rankingRepository.save(ranking);
			
		} else {
			
			System.out.println("atletica not found");
		}
					
					
				
		
	}*/
	
	@PutMapping("/atualizarPontuacao/{atletica_id}/{pontos}")
	public ResponseEntity<Integer> atualizarPontuacao(@PathVariable Long atletica_id, @PathVariable int pontos){
		
		rankingService.atualizarPontuacao(atletica_id, pontos);
		
		Optional<RankingEntity> ranking = rankingRepository.findById(atletica_id);
		if(ranking.isPresent()) {
			return ResponseEntity.ok(ranking.get().getPontuacaoTotal());
			
			
		}
		return ResponseEntity.notFound().build();
	}
}
