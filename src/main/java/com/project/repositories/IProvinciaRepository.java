package com.project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.project.model.Provincia;

@Repository
public interface IProvinciaRepository extends JpaRepository<Provincia, Integer> {
}