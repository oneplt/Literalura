package com.alura.literalura;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class GutendexRespuesta {
    private List<DatosLibro> results;

    public List<DatosLibro> getResults() {
        return results;
    }
    public void setResults(List<DatosLibro> results) {
        this.results = results;
    }
}
