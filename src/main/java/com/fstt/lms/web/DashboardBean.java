package com.fstt.lms.web;

import com.fstt.lms.service.BookService;
import com.fstt.lms.service.LoanService;
import com.fstt.lms.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

@Named
@RequestScoped
public class DashboardBean {

    @EJB
    private BookService bookService;

    @EJB
    private MemberService memberService;

    @EJB
    private LoanService loanService;

    private long totalBooks;
    private long totalMembers;
    private long activeLoans;
    private java.util.List<com.fstt.lms.model.Book> recentBooks;
    private java.util.List<com.fstt.lms.model.Book> lowStockBooks;

    @PostConstruct
    public void init() {
        totalBooks = bookService.count();
        totalMembers = memberService.count();
        activeLoans = loanService.countActive();
        recentBooks = bookService.findRecent(5);
        lowStockBooks = bookService.findLowStock(5);
    }

    public long getTotalBooks() {
        return totalBooks;
    }

    public long getTotalMembers() {
        return totalMembers;
    }

    public long getActiveLoans() {
        return activeLoans;
    }

    public java.util.List<com.fstt.lms.model.Book> getRecentBooks() {
        return recentBooks;
    }

    public java.util.List<com.fstt.lms.model.Book> getLowStockBooks() {
        return lowStockBooks;
    }
}
