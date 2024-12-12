package service;

import java.util.List;

import jakarta.ejb.Local;
import model.Member;

@Local
public interface MemberRegistrationLocal {
    void register(Member member);
    List<Member> listarClientes();
}
