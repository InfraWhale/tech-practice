package hellojpa;

import jakarta.persistence.*;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml에서 설정한 값

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            Address address = new Address("city", "street", "10000");

            Member member = new Member();
            member.setUserName("member1");
            member.setHomeAddress(address);
            em.persist(member);

            Address newAddress = new Address("NewCity", address.getStreet(), address.getZip());
            member.setHomeAddress(newAddress);

            tx.commit(); // 작업 끝나면 커밋 무조건 해줘야함 -> 이 시점에 db에 쿼리가 날아감
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close(); // 자원 닫아주기
        }
        
        emf.close(); // 자원 닫아주기
    }
        private static void logic(Member m1, Member m2) {
            System.out.println("m1 : " + (m1 instanceof Member) );
            System.out.println("m1 : " + (m2 instanceof Member) );
        }
}