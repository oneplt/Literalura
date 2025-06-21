package com.alura.literalura;

import com.alura.literalura.LibroService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication {

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Bean
	CommandLineRunner ejecutar(LibroService service) {
		return args -> {
			Scanner scanner = new Scanner(System.in);
			boolean continuar = true;

			while (continuar) {
				System.out.println("""
                        📚 MENÚ PRINCIPAL
                        1 - Buscar libro por título
                        2 - Listar libros registrados
                        3 - Listar autores registrados
                        4 - Listar autores vivos en un año
                        5 - Listar libros por idioma
                        6 - Salir
                        """);

				System.out.print("Selecciona una opción: ");
				String opcion = scanner.nextLine();

				switch (opcion) {
					case "1" -> service.iniciarBusqueda();
					case "2" -> service.listarLibros();
					case "3" -> service.listarAutores();
					case "4" -> service.autoresVivosEn();
					case "5" -> service.librosPorIdioma();
					case "6" -> {
						continuar = false;
						System.out.println("👋 ¡Hasta luego!");
					}
					default -> System.out.println("Opción inválida. Intenta de nuevo.");
				}
			}
		};
	}
}
