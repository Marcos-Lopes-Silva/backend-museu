package com.museu.museu.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import com.museu.museu.domain.ViagensPesquisa;

public interface ViagensPesquisaRepository extends JpaRepository<ViagensPesquisa, Integer>{
    

    Page<ViagensPesquisa> findAll(Pageable pageable);
}
