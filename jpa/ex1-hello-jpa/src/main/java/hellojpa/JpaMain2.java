package hellojpa;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class JpaMain2 {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml에서 설정한 값

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
//            // 비영속
//            Member member = new Member();
//            member.setId(101L);
//            member.setName("HelloJPA");
//
//            // 영속
//            System.out.println("=== BEFORE ===");
//            em.persist(member);
//            System.out.println("=== AFTER ===");
//
//            Member findMember = em.find(Member.class, 101L); // db가 아닌 1차캐시에서 먼저 조회
//
//            System.out.println("findMember.id = " + findMember.getId());
//            System.out.println("findMember.Name = " + findMember.getName());
///////////////////////////////////////////////////////////////////////////////////////
//            // 영속
//            Member findMember1 = em.find(Member.class, 101L); // 쿼리가 1번만 나감
//            Member findMember2 = em.find(Member.class, 101L); // db가 아닌 1차캐시에서 먼저 조회
//
//            System.out.println("result = " + (findMember1 == findMember2));
//////////////////////////////////////////////////////////////////////////////////////////
//            //영속
//            Member member1 = new Member(150L, "A");
//            Member member2 = new Member(160L, "A");
//
//            em.persist(member1);
//            em.persist(member2);
//
//            System.out.println("==============");
/////////////////////////////////////////////////////////////////////////////////
//            //영속
//            Member member = em.find(Member.class, 150L);
//            member.setName("ZZZZZ");
//
//            if(member.getName().equals("ZZZZZ")) {
//                em.persist(member);
//            }
//
//            // 수정 시에 em.persist(member) 필요없음
//
//            System.out.println("==============");
//////////////////////////////////////////////////////////////////////////////////

//            // 영속
//            Member member = new Member(200L, "member200");
//            em.persist(member);
//
//            em.flush();  // 즉시 쿼리가 나감. 보통은 커밋등등에서 자동으로 해줌. 1차캐시 지우진 않음
//
//            System.out.println("===================================");
///////////////////////////////////////////////////////////////////////////////////
            //영속
            Member member = em.find(Member.class, 150L);
            member.setName("AAAAA");

            //준영속
            //em.detach(member);
            em.clear();

            Member member2 = em.find(Member.class, 150L);

            System.out.println("===================================");
            tx.commit(); // 작업 끝나면 커밋 무조건 해줘야함 -> 이 시점에 db에 쿼리가 날아감
        } catch (Exception e) {
            tx.rollback();
        } finally {
            em.close(); // 자원 닫아주기
        }
        
        emf.close(); // 자원 닫아주기
    }
}