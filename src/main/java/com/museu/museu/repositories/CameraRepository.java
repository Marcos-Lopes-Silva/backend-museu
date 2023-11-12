package com.museu.museu.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.museu.museu.domain.Camera;

@Repository
public interface CameraRepository extends JpaRepository<Camera, Integer> {
}
