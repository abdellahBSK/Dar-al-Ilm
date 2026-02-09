package com.fstt.lms.service;

import com.fstt.lms.model.Member;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

@Stateless
public class MemberService {

    @PersistenceContext(unitName = "libraryPU")
    private EntityManager em;

    public void create(Member member) {
        em.persist(member);
    }

    public void update(Member member) {
        em.merge(member);
    }

    public void delete(Long id) {
        Member member = em.find(Member.class, id);
        if (member != null) {
            em.remove(member);
        }
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }

    public List<Member> findAll() {
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }
}
