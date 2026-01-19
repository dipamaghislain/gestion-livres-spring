package com.gestion_livres.repository;

import com.gestion_livres.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // Spring Data JPA génère automatiquement les méthodes CRUD
}