package ejb.proyecto1_cliente;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

import model.Member;
import service.MemberRegistrationRemote;

public class Main {
    private MemberRegistrationRemote memberRegistration;

    public void initialize() throws Exception {
        Hashtable<String, Object> jndiProps = new Hashtable<>();
        jndiProps.put(Context.INITIAL_CONTEXT_FACTORY, "org.wildfly.naming.client.WildFlyInitialContextFactory");
        jndiProps.put(Context.PROVIDER_URL, "http-remoting://localhost:8080");

        try {
            Context context = new InitialContext(jndiProps);
            memberRegistration = (MemberRegistrationRemote) context.lookup(
                "ejb:/proyecto1-server/MemberRegistration!service.MemberRegistrationRemote"
            );
        } catch (Exception e) {
            e.printStackTrace();
            System.err.println("Error JNDI lookup. ");
            throw e;
        }
    }

    public void registerMember(String name, String email, String phone) throws Exception {
        Member member = new Member();
        member.setName(name);
        member.setEmail(email);
        member.setPhoneNumber(phone);

        memberRegistration.register(member);
        System.out.println("Member registered: " + name);        
        getAllMembers();
    }
    
    // Nuevo método para obtener todos los miembros
    public List<Member> getAllMembers() throws Exception {
        return memberRegistration.listarClientes();
    }

    public static void main(String[] args) {
        try {
            Main client = new Main();
            client.initialize();
            client.registerMember("Erika", "erika@example.com", "1234567890");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
