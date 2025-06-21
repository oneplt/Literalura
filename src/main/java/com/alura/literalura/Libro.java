package com.alura.literalura;

import jakarta.persistence.*;

@Entity
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String autor;
    private String idioma;
    private Integer descargas;

    public void setTitulo(String donQuijote) {
    }

    public void setAutor(String miguelDeCervantes) {
    }

    public void setIdioma(String es) {
    }

    public void setDescargas(int i) {
    }

    public String getTitulo() {
        return "";
    }

    public String getAutor() {
        return null;
    }

    public String getIdioma() {
        return "";
    }

    // Getters y setters se puede usar Lombok en caso de haberse
    // agregado previamente)
}
