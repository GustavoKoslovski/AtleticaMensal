package com.atletica.mensal.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atletica.mensal.Entities.AtleticaEntity;
import com.atletica.mensal.Repository.AtleticaRepository;

@Service
public class AtleticaService {

	@Autowired
	private AtleticaRepository atleticaRepository;

	public AtleticaEntity salvarAtletica(AtleticaEntity atletica) {
		return atleticaRepository.save(atletica);
	}

	public List<AtleticaEntity> listarTodasAtleticas() {
		return atleticaRepository.findAll();
	}

	public Optional<AtleticaEntity> buscarAtleticaPorId(Long id) {
		return atleticaRepository.findById(id);
	}

	public void deletarAtletica(Long id) {
		AtleticaEntity atletica = buscarAtleticaPorId(id)
				.orElseThrow(() -> new RuntimeException("Atlética não encontrada."));
		atleticaRepository.delete(atletica);
	}
}
