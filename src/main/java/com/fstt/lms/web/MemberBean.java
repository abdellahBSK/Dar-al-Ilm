package com.fstt.lms.web;

import com.fstt.lms.model.Member;
import com.fstt.lms.service.MemberService;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class MemberBean implements Serializable {

    @Inject
    private MemberService memberService;

    private List<Member> members;
    private Member member = new Member();

    private Long id; // For view param

    @PostConstruct
    public void init() {
        members = memberService.findAll();
    }

    public void loadMember() {
        if (id != null) {
            this.member = memberService.find(id);
        }
    }

    public List<Member> getMembers() {
        return members;
    }

    public void delete(Member member) {
        memberService.delete(member.getId());
        members.remove(member);
    }

    public String prepareCreate() {
        this.member = new Member();
        return "member-form?faces-redirect=true";
    }

    public String prepareEdit(Member member) {
        return "member-form?faces-redirect=true&id=" + member.getId();
    }

    public String save() {
        if (member.getId() == null) {
            memberService.create(member);
        } else {
            memberService.update(member);
        }
        return "members?faces-redirect=true";
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
