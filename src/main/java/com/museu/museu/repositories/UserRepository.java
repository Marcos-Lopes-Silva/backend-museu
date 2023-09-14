package com.museu.museu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.museu.museu.domain.Usuario;

public interface UserRepository  extends JpaRepository<Usuario, Integer>{

    UserDetails findByEmail(String subject);
    
}
