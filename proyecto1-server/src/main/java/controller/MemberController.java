package controller;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.EJB;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;
import model.Member;
import service.MemberRegistrationLocal;

@Named
@RequestScoped
public class MemberController {

    @EJB
    private MemberRegistrationLocal memberRegistration;

    private Member newMember;

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
    }

    public void register() {
        try {
            memberRegistration.register(newMember);
            System.out.println("Member registered successfully: " + newMember.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Getters y setters
    public Member getNewMember() { return newMember; }
    public void setNewMember(Member newMember) { this.newMember = newMember; }
}
