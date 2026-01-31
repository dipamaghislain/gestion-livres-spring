package com.gestion_livres.service;

import com.gestion_livres.entity.Book;
import com.gestion_livres.exception.ResourceNotFoundException;
import com.gestion_livres.repository.BookRepository;
import com.gestion_livres.service.impl.BookServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookServiceTest {

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private BookServiceImpl bookService;

    @Test
    void shouldReturnAllBooks() {
        Book book1 = new Book("Titre 1", "Auteur 1", 20.0);
        Book book2 = new Book("Titre 2", "Auteur 2", 30.0);
        when(bookRepository.findAll()).thenReturn(Arrays.asList(book1, book2));

        List<Book> result = bookService.getAllBooks();

        assertEquals(2, result.size());
        verify(bookRepository, times(1)).findAll();
    }

    @Test
    void shouldReturnBookById() {
        Book book = new Book("Titre", "Auteur", 20.0);
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        Book result = bookService.getBookById(1L);

        assertNotNull(result);
        assertEquals("Titre", result.getTitre());
    }

    @Test
    void shouldThrowExceptionWhenBookNotFound() {
        when(bookRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> bookService.getBookById(1L));
    }

    @Test
    void shouldSaveBook() {
        Book book = new Book("Nouveau", "Auteur", 15.0);
        when(bookRepository.save(any(Book.class))).thenReturn(book);

        Book result = bookService.saveBook(book);

        assertNotNull(result);
        assertEquals("Nouveau", result.getTitre());
        verify(bookRepository, times(1)).save(book);
    }

    @Test
    void shouldUpdateBook() {
        Book existingBook = new Book("Ancien", "Auteur", 20.0);
        existingBook.setId(1L);
        
        Book updateDetails = new Book("Nouveau Titre", "Nouvel Auteur", 25.0);
        
        when(bookRepository.findById(1L)).thenReturn(Optional.of(existingBook));
        when(bookRepository.save(any(Book.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Book result = bookService.updateBook(1L, updateDetails);

        assertEquals("Nouveau Titre", result.getTitre());
        assertEquals("Nouvel Auteur", result.getAuteur());
        assertEquals(25.0, result.getPrix());
    }

    @Test
    void shouldDeleteBook() {
        Book book = new Book("Delete Me", "Auteur", 10.0);
        book.setId(1L);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

        bookService.deleteBook(1L);

        verify(bookRepository, times(1)).delete(book);
    }
}
