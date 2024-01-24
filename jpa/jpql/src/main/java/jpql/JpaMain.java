package jpql;

import jakarta.persistence.*;

import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml에서 설정한 값

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);
            em.persist(member);

            // getResultList
/*            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class); // 반환 타입 명확
            TypedQuery<String> query2 = em.createQuery("select m.username from Member m", String.class); // 반환 타입 명확
            Query query3 = em.createQuery("select m.username, m.age from Member m"); // 반환 타입 명확 X

            List<Member> resultList = query.getResultList();

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1);
            }*/

            // getSingleResult
/*            TypedQuery<Member> query = em.createQuery("select m from Member m", Member.class);
            //TypedQuery<Member> query = em.createQuery("select m from Member m where m.id = 10", Member.class);

            Member result = query.getSingleResult(); // 결과 하나 아닐 경우 바로 예외 띄움
            System.out.println("result = " + result);*/

            //파라미터바인딩
//            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);
//            query.setParameter("username", "member1");
//            Member singleResult = query.getSingleResult();
            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();

            System.out.println("result = " + result.getUsername());

            tx.commit(); // 작업 끝나면 커밋 무조건 해줘야함 -> 이 시점에 db에 쿼리가 날아감
        } catch (Exception e) {
            tx.rollback();
            e.printStackTrace();
        } finally {
            em.close(); // 자원 닫아주기
        }
        
        emf.close(); // 자원 닫아주기
    }
}