package jpql;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        final EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        final EntityManager em = emf.createEntityManager();

        final EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            final Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);


            final Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();
            System.out.println("result = " + result.getUsername());

            //TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class);
            //Query query3 = em.createQuery("select m.username m.age from Member m");

            tx.commit();
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }

        emf.close();
    }
}
