package com.fstt.lms.service;

import com.fstt.lms.model.Book;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class BookService {

    @PersistenceContext(unitName = "libraryPU")
    private EntityManager em;

    public void create(Book book) {
        em.persist(book);
    }

    public void update(Book book) {
        em.merge(book);
    }

    public void delete(Long id) {
        Book book = em.find(Book.class, id);
        if (book != null) {
            em.remove(book);
        }
    }

    public Book find(Long id) {
        return em.find(Book.class, id);
    }

    public List<Book> findAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}
