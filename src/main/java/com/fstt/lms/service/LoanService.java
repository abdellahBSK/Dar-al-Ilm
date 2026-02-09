package com.fstt.lms.service;

import com.fstt.lms.model.Book;
import com.fstt.lms.model.Loan;
import com.fstt.lms.model.Member;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Stateless
public class LoanService {

    @PersistenceContext(unitName = "libraryPU")
    private EntityManager em;

    public void borrowBook(Member member, Book book) throws Exception {
        // Check availability
        if (book.getQuantity() <= 0) {
            throw new Exception("Book is not available.");
        }

        // Decrement quantity
        book.setQuantity(book.getQuantity() - 1);
        em.merge(book);

        // Create loan
        Loan loan = new Loan();
        loan.setMember(member);
        loan.setBook(book);
        loan.setLoanDate(new Date());

        // Calculate return date (1 week later)
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.WEEK_OF_YEAR, 1);
        loan.setReturnDate(cal.getTime());

        loan.setReturned(false);

        em.persist(loan);
    }

    public void returnBook(Loan loan) {
        if (loan.isReturned())
            return;

        loan.setReturned(true);
        em.merge(loan);

        // Increment book quantity
        Book book = loan.getBook();
        book.setQuantity(book.getQuantity() + 1);
        em.merge(book);
    }

    public List<Loan> findAllActive() {
        return em.createQuery("SELECT l FROM Loan l WHERE l.returned = false", Loan.class).getResultList();
    }

    public List<Loan> findAll() {
        return em.createQuery("SELECT l FROM Loan l", Loan.class).getResultList();
    }

    public long countActive() {
        return em.createQuery("SELECT COUNT(l) FROM Loan l WHERE l.returned = false", Long.class).getSingleResult();
    }
}
