package com.atletica.mensal.Entities;


import java.util.Set;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.sym.Name;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;
	private String email;
	private String senha;

/*	@ManyToMany
	@JoinTable(name = "user_atletica", joinColumns = @Joi("user_id"), inverseJoinColumns = @JoinColumn(name = "atletica_id"))*/
	
	@ManyToMany
	@JoinTable(
			name = "user_atletica",
			joinColumns = @JoinColumn(name = "user_id"),
			inverseJoinColumns = @JoinColumn(name = "atletica_id")
			)
	private Set<AtleticaEntity> atleticas;

	private boolean isDonoAtletica;

}
