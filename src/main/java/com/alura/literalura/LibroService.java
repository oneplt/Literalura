package com.alura.literalura;

import com.alura.literalura.DatosLibro;
import com.alura.literalura.GutendexRespuesta;
import com.alura.literalura.Libro;
import com.alura.literalura.repository.LibroRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class LibroService {

    private final LibroRepository repository;
    private final ConsumoApi api;

    public LibroService(LibroRepository repository, ConsumoApi api) {
        this.repository = repository;
        this.api = api;
    }

    // ✅ Buscar libro por título y registrar en BD
    public void iniciarBusqueda() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("🔎 Ingrese el título del libro: ");
        String titulo = scanner.nextLine();

        if (repository.findByTituloIgnoreCase(titulo).isPresent()) {
            System.out.println("⚠️ El libro ya está registrado.");
            return;
        }

        String json = api.buscarLibro(titulo);
        ObjectMapper mapper = new ObjectMapper();

        try {
            GutendexRespuesta respuesta = mapper.readValue(json, GutendexRespuesta.class);
            if (respuesta.getResults().isEmpty()) {
                System.out.println("❌ Libro no encontrado.");
                return;
            }

            DatosLibro datos = respuesta.getResults().get(0);
            Libro libro = new Libro();
            libro.setTitulo(datos.getTitle());
            libro.setAutor(datos.getAutorPrincipal());
            libro.setIdioma(datos.getLanguage());
            libro.setDescargas(Integer.parseInt(datos.getDownload_count()));

            repository.save(libro);
            System.out.println("✅ Libro guardado exitosamente.");

        } catch (Exception e) {
            System.out.println("❌ Error al procesar la respuesta de la API.");
            e.printStackTrace();
        }
    }

    // ✅ Listar libros registrados
    public void listarLibros() {
        List<Libro> libros = repository.findAll();
        if (libros.isEmpty()) {
            System.out.println("📭 No hay libros registrados.");
        } else {
            System.out.println("📚 Libros registrados:");
            libros.forEach(libro -> System.out.println("🔸 " + libro.getTitulo() + " - " + libro.getAutor()));
        }
    }

    // ✅ Listar autores registrados sin repetir
    public void listarAutores() {
        List<Libro> libros = repository.findAll();
        Set<String> autores = new HashSet<>();
        for (Libro libro : libros) {
            Object libroAutor = libro.getAutor();
            autores.add((String) libroAutor);
        }

        if (autores.isEmpty()) {
            System.out.println("📭 No hay autores registrados.");
        } else {
            System.out.println("👨‍🏫 Autores registrados:");
            autores.forEach(autor -> System.out.println("🔹 " + autor));
        }
    }

    // ✅ Mostrar autores vivos en un año (simulado)
    public void autoresVivosEn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("📅 Ingresa el año (ej: 1600): ");
        int año = Integer.parseInt(scanner.nextLine());

        // Simulación con autores históricos
        List<String> autores = repository.findAll().stream()
                .filter(libro -> {
                    String autor = libro.getAutor();
                    return (autor.contains("shakespeare") && año >= 1564 && año <= 1616) ||
                            (autor.contains("cervantes") && año >= 1547 && año <= 1616) ||
                            (autor.contains("austen") && año >= 1775 && año <= 1817);
                })
                .map(Libro::getAutor)
                .distinct()
                .toList();

        if (autores.isEmpty()) {
            System.out.println("📭 No se encontraron autores vivos en ese año.");
        } else {
            System.out.println("📖 Autores vivos en " + año + ":");
            autores.forEach(a -> System.out.println("🔹 " + a));
        }
    }

    // ✅ Listar libros por idioma
    public void librosPorIdioma() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("🌐 Ingresa el código de idioma (ES, EN, FR, PT): ");
        String idioma = scanner.nextLine().toLowerCase();

        List<Libro> libros = repository.findAll().stream()
                .filter(l -> l.getIdioma().equalsIgnoreCase(idioma))
                .toList();

        if (libros.isEmpty()) {
            System.out.println("📭 No hay libros en ese idioma.");
        } else {
            System.out.println("📘 Libros en idioma " + idioma.toUpperCase() + ":");
            libros.forEach(l -> System.out.println("🔸 " + l.getTitulo()));
        }
    }
}
