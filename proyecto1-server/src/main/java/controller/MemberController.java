package controller;

import java.util.List;

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
    private List<Member> members;

    @PostConstruct
    public void initNewMember() {
        newMember = new Member();
        loadMembers();
    }

    public void register() {
        try {
            memberRegistration.register(newMember);
            System.out.println("Member registered successfully: " + newMember.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
 // Método para cargar la lista de miembros
    public void loadMembers() {
        try {
            members = memberRegistration.listarClientes();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


  
    // Getters y setters
    public Member getNewMember() { 
        return newMember; 
    }

    public void setNewMember(Member newMember) { 
        this.newMember = newMember; 
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }
}
