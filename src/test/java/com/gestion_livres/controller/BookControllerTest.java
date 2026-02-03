package com.gestion_livres.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gestion_livres.entity.Book;
import com.gestion_livres.service.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(BookController.class)
class BookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookService bookService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldReturnPagedBooks() throws Exception {
        Book book = new Book("Titre Test", "Auteur Test", 25.50);
        Page<Book> page = new PageImpl<>(Arrays.asList(book));
        
        when(bookService.getBooks(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/books")
                .param("page", "0")
                .param("size", "10")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content", hasSize(1)))
                .andExpect(jsonPath("$.content[0].titre").value("Titre Test"));
    }

    @Test
    void shouldCreateBookWhenValid() throws Exception {
        Book book = new Book("Valid Title", "Valid Author", 20.0);
        when(bookService.saveBook(any(Book.class))).thenReturn(book);

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(book)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.titre").value("Valid Title"));
    }

    @Test
    void shouldReturnBadRequestWhenCreatingInvalidBook() throws Exception {
        Book invalidBook = new Book("", "", -10.0); // Invalid: empty title, negative price

        mockMvc.perform(post("/api/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(invalidBook)))
                .andExpect(status().isBadRequest())
                // Check if validation error keys exist
                .andExpect(jsonPath("$.titre").exists())
                .andExpect(jsonPath("$.prix").exists());
    }

    @Test
    void shouldReturnBookById() throws Exception {
        Book book = new Book("Titre", "Auteur", 10.0);
        book.setId(1L);
        when(bookService.getBookById(1L)).thenReturn(book);

        mockMvc.perform(get("/api/books/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }
}
