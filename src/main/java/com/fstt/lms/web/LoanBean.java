package com.fstt.lms.web;

import com.fstt.lms.model.Book;
import com.fstt.lms.model.Loan;
import com.fstt.lms.model.Member;
import com.fstt.lms.service.BookService;
import com.fstt.lms.service.LoanService;
import com.fstt.lms.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class LoanBean implements Serializable {

    @Inject
    private LoanService loanService;

    @Inject
    private BookService bookService;

    @Inject
    private MemberService memberService;

    private List<Loan> loans;

    private Long selectedMemberId;
    private Long selectedBookId;

    private List<Member> availableMembers;
    private List<Book> availableBooks;

    @PostConstruct
    public void init() {
        refreshLoans();
    }

    public void refreshLoans() {
        loans = loanService.findAll();
    }

    public void prepareCreate() {
        availableMembers = memberService.findAll();
        availableBooks = bookService.findAll(); // Should filter by quantity > 0 ideally or handle in service
        selectedMemberId = null;
        selectedBookId = null;
    }

    public String createLoan() {
        try {
            Member member = memberService.find(selectedMemberId);
            Book book = bookService.find(selectedBookId);

            if (member != null && book != null) {
                loanService.borrowBook(member, book);
                refreshLoans();
                return "loans?faces-redirect=true";
            } else {
                FacesContext.getCurrentInstance().addMessage(null,
                        new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid selection",
                                "Please select member and book."));
                return null;
            }
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage()));
            return null;
        }
    }

    public void returnBook(Loan loan) {
        loanService.returnBook(loan);
        refreshLoans();
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public Long getSelectedMemberId() {
        return selectedMemberId;
    }

    public void setSelectedMemberId(Long selectedMemberId) {
        this.selectedMemberId = selectedMemberId;
    }

    public Long getSelectedBookId() {
        return selectedBookId;
    }

    public void setSelectedBookId(Long selectedBookId) {
        this.selectedBookId = selectedBookId;
    }

    public List<Member> getAvailableMembers() {
        if (availableMembers == null)
            availableMembers = memberService.findAll();
        return availableMembers;
    }

    public List<Book> getAvailableBooks() {
        if (availableBooks == null)
            availableBooks = bookService.findAll();
        return availableBooks;
    }
}
