package com.museu.museu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.museu.museu.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
    
    Page<Funcionario> findAll(Pageable pageable);

    Funcionario findByUsuarioId(Integer user);
}
