package com.alura.literalura;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DatosLibro {

    private String title;

    @JsonProperty("languages")
    private List<String> language;

    @JsonProperty("download_count")
    private int download_count;

    @JsonProperty("authors")
    private List<AutorDTO> authors;

    public String getTitle() {
        return title;
    }

    public String getLanguage() {
        return language != null && !language.isEmpty() ? language.get(0) : "desconocido";
    }

    public String getDownload_count() {
        return String.valueOf(download_count);
    }

    public String getAutorPrincipal() {
        return (authors != null && !authors.isEmpty()) ? authors.get(0).getName() : "desconocido";
    }
}
