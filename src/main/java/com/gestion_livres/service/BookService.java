package com.gestion_livres.service;

import com.gestion_livres.entity.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookService {
    List<Book> getAllBooks();
    Page<Book> getBooks(Pageable pageable);
    Book getBookById(Long id);
    Book saveBook(Book book);
    Book updateBook(Long id, Book bookDetails);
    void deleteBook(Long id);
}
