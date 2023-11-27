package com.museu.museu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.museu.museu.domain.Exposicao;

public interface ExposicaoRepository extends JpaRepository<Exposicao, Integer> {
    
}
