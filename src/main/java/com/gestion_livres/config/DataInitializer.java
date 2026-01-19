package com.gestion_livres.config;

import com.gestion_livres.entity.Book;
import com.gestion_livres.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    CommandLineRunner initDatabase(BookRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                repository.save(new Book("Le Petit Prince", "Antoine de Saint-Exupéry", 12.50));
                repository.save(new Book("1984", "George Orwell", 15.00));
                repository.save(new Book("Harry Potter à l'école des sorciers", "J.K. Rowling", 20.00));
                repository.save(new Book("Le Seigneur des Anneaux", "J.R.R. Tolkien", 25.00));
                repository.save(new Book("L'Alchimiste", "Paulo Coelho", 18.00));
                repository.save(new Book("Sapiens", "Yuval Noah Harari", 22.00));
                repository.save(new Book("La Peste", "Albert Camus", 14.50));
                repository.save(new Book("Cent ans de solitude", "Gabriel García Márquez", 19.00));

                System.out.println("✅ Base de données initialisée avec " + repository.count() + " livres");
            }
        };
    }
}