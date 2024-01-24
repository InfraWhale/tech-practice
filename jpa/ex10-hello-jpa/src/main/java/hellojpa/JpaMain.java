package hellojpa;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Hibernate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml에서 설정한 값

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {
            // JPQL 소개
/*            List<Member> result = em.createQuery(
                    "SELECT m FROM Member m WHERE m.userName LIKE '%kim%'",
                    Member.class
            ).getResultList();

            for (Member member : result) {
                System.out.println("member = " + member);
            }*/

            //Criteria 사용 준비 (실무에선 잘 안씀)
/*            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Member> query = cb.createQuery(Member.class);

            Root<Member> m = query.from(Member.class);

            CriteriaQuery<Member> cq = query.select(m);

            String username = "dsafas";
            if(username != null) {
                cq = cq.where(cb.equal(m.get("userName"), "kim"));
            }

            List<Member> resultList = em.createQuery(cq).getResultList();*/

            //NativeQuery (잘 안씀)
/*            Member member = new Member();
            member.setUserName("member1");
            em.persist(member);

            // flush() -> commit, query 날아갈때 발동함
            // 즉 이때는 flush 안해줘도 됨
            List<Member> resultList =  em.createNativeQuery("select MEMBER_ID, city, street, zipcode, USERNAME from MEMBER")
                    .getResultList();

            // 단, JDBC 커넥션을 직접 사용하거나, 스프링 JdbcTemplate, 마이바티스등 사용 시엔 flush() 강제사용 필요

            System.out.println("==============================================");

            for (Member member1 : resultList) {
                System.out.println("member1 = " + member1 );
            }*/

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