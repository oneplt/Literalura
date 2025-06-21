package com.alura.literalura;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AutorDTO {
    private String name;

    public String getName() {
        return name;
    }
}
