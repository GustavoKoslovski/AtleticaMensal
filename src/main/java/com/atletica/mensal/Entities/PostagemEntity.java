package com.atletica.mensal.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class PostagemEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long valor;
	private String observacao;
	private String lote;
	private String whatsappLink;
	private LocalDateTime dataPostagem;
	private String imagem; 

	@ManyToOne
	@JoinColumn(name = "atletica_id")
	private AtleticaEntity atletica;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserEntity user;

}
