package com.atletica.mensal.Entities;

import java.util.Set;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
@Entity
@Data
public class AtleticaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String universidade;

    @OneToMany(mappedBy = "atletica", cascade = CascadeType.ALL)
    private Set<PostagemEntity> postagens;
    
    @Column(name = "dono_id")
    private Long donoId; 

}
