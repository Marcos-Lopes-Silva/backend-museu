package com.museu.museu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.museu.museu.domain.Usuario;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String subject);

}
