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

    public long count() {
        return em.createQuery("SELECT COUNT(b) FROM Book b", Long.class).getSingleResult();
    }

    public List<Book> search(String query) {
        return em.createQuery(
                "SELECT b FROM Book b WHERE LOWER(b.title) LIKE :query OR LOWER(b.author) LIKE :query OR LOWER(b.isbn) LIKE :query",
                Book.class)
                .setParameter("query", "%" + query.toLowerCase() + "%")
                .getResultList();
    }

    public List<Book> findRecent(int limit) {
        return em.createQuery("SELECT b FROM Book b ORDER BY b.id DESC", Book.class)
                .setMaxResults(limit)
                .getResultList();
    }

    public List<Book> findLowStock(int limit) {
        return em.createQuery("SELECT b FROM Book b WHERE b.quantity < 5 ORDER BY b.quantity ASC", Book.class)
                .setMaxResults(limit)
                .getResultList();
    }
}
