package com.atletica.mensal.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atletica.mensal.Entities.PostagemEntity;

public interface PostagemRepository extends JpaRepository<PostagemEntity, Long> {
	 List<PostagemEntity> findByAtleticaId(Long atletica_id);
	 
}