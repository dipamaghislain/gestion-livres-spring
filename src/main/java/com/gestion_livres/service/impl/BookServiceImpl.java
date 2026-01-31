package com.gestion_livres.service.impl;

import com.gestion_livres.entity.Book;
import com.gestion_livres.exception.ResourceNotFoundException;
import com.gestion_livres.repository.BookRepository;
import com.gestion_livres.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livre non trouvé avec l'id : " + id));
    }

    @Override
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book updateBook(Long id, Book bookDetails) {
        Book existingBook = getBookById(id);
        
        existingBook.setTitre(bookDetails.getTitre());
        existingBook.setAuteur(bookDetails.getAuteur());
        existingBook.setPrix(bookDetails.getPrix());

        return bookRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        Book existingBook = getBookById(id);
        bookRepository.delete(existingBook);
    }
}
