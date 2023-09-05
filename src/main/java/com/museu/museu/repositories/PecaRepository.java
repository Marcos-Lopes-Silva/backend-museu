package com.museu.museu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.museu.museu.domain.Peca;

public interface PecaRepository extends JpaRepository<Peca, Integer>{
    
}
