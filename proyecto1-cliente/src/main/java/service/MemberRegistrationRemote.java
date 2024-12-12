package service;

import java.util.List;

import model.Member;

public interface MemberRegistrationRemote {
	void register(Member member) throws Exception;
	List<Member> listarClientes();
}
