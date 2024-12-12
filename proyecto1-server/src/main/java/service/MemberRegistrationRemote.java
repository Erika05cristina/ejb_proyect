package service;

import jakarta.ejb.Remote;
import model.Member;

@Remote
public interface MemberRegistrationRemote {
    void register(Member member);
}