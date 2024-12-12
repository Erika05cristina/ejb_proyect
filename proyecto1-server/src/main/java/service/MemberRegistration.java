package service;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import model.Member;

@Stateless
public class MemberRegistration implements MemberRegistrationLocal, MemberRegistrationRemote {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void register(Member member) {
        em.persist(member);
    }
}