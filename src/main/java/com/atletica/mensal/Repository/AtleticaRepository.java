package com.atletica.mensal.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.atletica.mensal.Entities.AtleticaEntity;

public interface AtleticaRepository extends JpaRepository<AtleticaEntity, Long> {
}