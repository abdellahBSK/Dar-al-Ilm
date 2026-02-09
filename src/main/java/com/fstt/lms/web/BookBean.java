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
    private Long id; // For view param
    private String searchQuery;

    @PostConstruct
    public void init() {
        books = bookService.findAll();
    }

    public void loadBook() {
        if (id != null) {
            this.book = bookService.find(id);
        }
    }

    public void search() {
        if (searchQuery != null && !searchQuery.trim().isEmpty()) {
            books = bookService.search(searchQuery);
        } else {
            books = bookService.findAll();
        }
    }

    public List<Book> getBooks() {
        return books;
    }

    public void delete(Book book) {
        try {
            bookService.delete(book.getId());
            books.remove(book);
            jakarta.faces.context.FacesContext.getCurrentInstance().addMessage(null,
                    new jakarta.faces.application.FacesMessage(jakarta.faces.application.FacesMessage.SEVERITY_INFO,
                            "Book deleted", null));
        } catch (Exception e) {
            jakarta.faces.context.FacesContext.getCurrentInstance().addMessage(null,
                    new jakarta.faces.application.FacesMessage(jakarta.faces.application.FacesMessage.SEVERITY_ERROR,
                            "Cannot delete book. It might be referenced by active loans.", null));
        }
    }

    public String prepareCreate() {
        this.book = new Book();
        return "book-form?faces-redirect=true";
    }

    public String prepareEdit(Book book) {
        return "book-form?faces-redirect=true&id=" + book.getId();
    }

    public String save() {
        System.out.println("Saving book: " + book);
        System.out.println("Book ID: " + book.getId());
        if (book.getId() == null) {
            System.out.println("Creating new book...");
            bookService.create(book);
        } else {
            System.out.println("Updating existing book...");
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSearchQuery() {
        return searchQuery;
    }

    public void setSearchQuery(String searchQuery) {
        this.searchQuery = searchQuery;
    }
}
