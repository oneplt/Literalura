package com.alura.literalura.repository;
import com.alura.literalura.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface LibroRepository extends JpaRepository <Libro,Long>{
    Optional<Object> findByTituloIgnoreCase(String titulo);
}