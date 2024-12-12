package service;

import java.util.List;

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
    
    @Override
    public List<Member> listarClientes() {
        return em.createQuery("SELECT m FROM Member m", Member.class).getResultList();
    }
}