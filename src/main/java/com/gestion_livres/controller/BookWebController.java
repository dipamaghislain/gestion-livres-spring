package com.gestion_livres.controller;

import com.gestion_livres.entity.Book;
import com.gestion_livres.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/books")
public class BookWebController {

    private final BookService bookService;

    public BookWebController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public String list(Model model, @ModelAttribute("successMessage") String successMessage) {
        model.addAttribute("books", bookService.getAllBooks());
        if (successMessage != null && !successMessage.isBlank()) {
            model.addAttribute("successMessage", successMessage);
        }
        return "books/list";
    }

    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("formTitle", "Ajouter un livre");
        model.addAttribute("action", "/books");
        return "books/form";
    }

    @PostMapping
    public String create(@Valid @ModelAttribute("book") Book book,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formTitle", "Ajouter un livre");
            model.addAttribute("action", "/books");
            return "books/form";
        }
        bookService.saveBook(book);
        redirectAttributes.addFlashAttribute("successMessage", "Livre ajouté avec succès");
        return "redirect:/books";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);
        model.addAttribute("formTitle", "Modifier un livre");
        model.addAttribute("action", "/books/" + id);
        return "books/form";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable Long id,
                         @Valid @ModelAttribute("book") Book book,
                         BindingResult bindingResult,
                         Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("formTitle", "Modifier un livre");
            model.addAttribute("action", "/books/" + id);
            return "books/form";
        }
        bookService.updateBook(id, book);
        redirectAttributes.addFlashAttribute("successMessage", "Livre modifié avec succès");
        return "redirect:/books";
    }

    @PostMapping("/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookService.deleteBook(id);
        redirectAttributes.addFlashAttribute("successMessage", "Livre supprimé avec succès");
        return "redirect:/books";
    }
}
