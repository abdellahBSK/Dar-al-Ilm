package com.fstt.lms.web;

import com.fstt.lms.model.Book;
import com.fstt.lms.service.BookService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class BookBean implements Serializable {

    @Inject
    private BookService bookService;

    private List<Book> books;
    private Book book = new Book();

    @PostConstruct
    public void init() {
        books = bookService.findAll();
    }

    public List<Book> getBooks() {
        return books;
    }

    public void delete(Book book) {
        bookService.delete(book.getId());
        books.remove(book);
    }

    public String prepareCreate() {
        this.book = new Book();
        return "book-form?faces-redirect=true";
    }

    public String prepareEdit(Book book) {
        this.book = book;
        return "book-form?faces-redirect=true";
    }

    public String save() {
        if (book.getId() == null) {
            bookService.create(book);
        } else {
            bookService.update(book);
        }
        return "books?faces-redirect=true";
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
