package service;

import java.util.List;

import jakarta.ejb.Remote;
import model.Member;

@Remote
public interface MemberRegistrationRemote {
    void register(Member member);
    List<Member> listarClientes(); // Método para listar clientes
    
}