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
//            Member member = new Member();
//            member.setUsername("member1");
//            member.setAge(10);
//            em.persist(member);

//            em.flush();
//            em.clear();

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
/*            TypedQuery<Member> query = em.createQuery("select m from Member m where m.username = :username", Member.class);
            query.setParameter("username", "member1");
            Member singleResult = query.getSingleResult();

            Member result = em.createQuery("select m from Member m where m.username = :username", Member.class)
                    .setParameter("username", "member1")
                    .getSingleResult();

            System.out.println("result = " + result.getUsername());*/

            // 프로젝션
                // 엔티티 프로젝션 1
/*            List<Member> result = em.createQuery("select m from Member m", Member.class)
                            .getResultList();

            Member findMember = result.get(0);
            findMember.setAge(20);*/
            
                // 엔티티 프로젝션 2
/*            List<Team> result = em.createQuery("select m.team from Member m", Team.class)
                .getResultList();*/
                // 이렇게 하는게 더 좋음
/*            List<Team> result = em.createQuery("select t from Member m join m.team t", Team.class)
                    .getResultList();*/

                // 임베디드 타입 프로젝션 (임베디드 타입 만으로는 프로젝션이 안됨)
/*            List<Address> result = em.createQuery("select o.address from Order o", Address.class)
                .getResultList();*/

            // 스칼라 타입 프로젝션 (여러 값 조회)
/*            List resultList = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();

            Object o = resultList.get(0);
            Object[] result = (Object[]) o;

            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);*/

            // 여러값 조회 2
/*            List<Object[]> resultList = em.createQuery("select m.username, m.age from Member m")
                    .getResultList();

            Object[] result = resultList.get(0);

            System.out.println("username = " + result[0]);
            System.out.println("age = " + result[1]);*/

            // 여러값 조회 3 (가장 깔끔)
/*            List<MemberDTO> result = em.createQuery("select new jpql.MemberDTO(m.username, m.age) from Member m", MemberDTO.class)
                    .getResultList();

            MemberDTO memberDTO = result.get(0);
            System.out.println("username = " + memberDTO.getUsername());
            System.out.println("age = " + memberDTO.getAge());*/

            // 페이징
/*            for (int i = 0; i < 100; i++) {
                Member member2 = new Member();
                member2.setUsername("member" + i);
                member2.setAge(i);
                em.persist(member2);
            }

            em.flush();
            em.clear();

            List<Member> result = em.createQuery("select m from Member m order by m.age desc", Member.class)
                    .setFirstResult(11)
                    .setMaxResults(10)
                    .getResultList();

            System.out.println("result.size = " + result.size());
            for (Member member1 : result) {
                System.out.println("member1 = " + member1);
            }*/
            
            // 조인

            Team team = new Team();
            team.setName("teamA");
            em.persist(team);

            Member member = new Member();
            member.setUsername("member1");
            member.setAge(10);

            member.changeTeam(team);

            em.persist(member);

            em.flush();
            em.clear();

//            String query = "select m from Member m inner join m.team t";
//            String query = "select m from Member m left outer join m.team t";
//            String query = "select m from Member m, Team t where m.username = t.name";

//            String query = "select m from Member m left join m.team t on t.name = 'teamA'"; // 조인 대상 필더틸

            String query = "select m from Member m left join Team t on m.username = t.name"; // 연관관계 없는 엔티티 외부 조인

            List<Member> result = em.createQuery(query, Member.class)
                    .getResultList();

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