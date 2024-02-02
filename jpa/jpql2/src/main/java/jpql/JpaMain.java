package jpql;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello"); // persistence.xml에서 설정한 값

        EntityManager em =  emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try {

            // 경로 표현식

/*            Team team = new Team();
            em.persist(team);

            Member member1 = new Member();
            member1.setUsername("관리자1");
            member1.setTeam(team);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("관리자2");
            member2.setTeam(team);
            em.persist(member2);

            em.flush();
            em.clear();

//            String query = "select m.username From Member m"; // 상태 필드
//            List<String> result = em.createQuery(query, String.class)
//                    .getResultList();
//
//            for (String s: result) {
//                System.out.println("s = " + s);
//            }

//            String query = "select m.team From Member m"; // 단일 값 연관 경로
//            // 묵시적 내부 조인 발생 (조인 넘 많이지면 성능 떨어지고 튜닝하기 어려움)
//            List<Team> result = em.createQuery(query, Team.class)
//                            .getResultList();
//
//            for (Team t: result) {
//                System.out.println("t = " + t);
//            }

//            String query = "select t.members From Team t"; // 컬렉션 값 연관 경로
//
//            Collection result = em.createQuery(query, Collection.class)
//                            .getResultList();
//
//            for (Object o: result) {
//                System.out.println("o = " + o);
//            }

//            String query = "select t.members From Team t";
            //  t.members 다음 탐색이 안됨
            // 해결하려면 명시적 join 사용 @@@ 묵시적조인은 사용하지 말자!!!
//            String query = "select m From Team t join t.members m";
            // 이럴경우 별칭 사용 가능
//            String query = "select m.username From Team t join t.members m";
//
//            Collection result = em.createQuery(query, Collection.class)
//                    .getResultList();
//
//            System.out.println("result = " + result);*/

            // fetch join @@@ 매우 중요!!!!!!!!

            Team teamA = new Team();
            teamA.setName("팀A");
            em.persist(teamA);

            Team teamB = new Team();
            teamB.setName("팀B");
            em.persist(teamB);

            Member member1 = new Member();
            member1.setUsername("회원1");
            member1.setTeam(teamA);
            em.persist(member1);

            Member member2 = new Member();
            member2.setUsername("회원2");
            member2.setTeam(teamA);
            em.persist(member2);

            Member member3 = new Member();
            member3.setUsername("회원3");
            member3.setTeam(teamB);
            em.persist(member3);

            em.flush();
            em.clear();

//            String query = "select m From Member m";
//
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//
//            for (Member member : result) {
//                //System.out.println("member = " + member);
//                System.out.println("member name = " + member.getUsername() + ", " + member.getTeam().getName());
//                //회원1, 팀A(SQL)
//                //회원2, 팀A(1차캐시)
//                //회원3, 팀B(SQL) 1차캐시에 없으므로
//
//                // 회원 100명이 팀 다 다르면? -> N + 1
//
//                // 이 경우 fetch join으로 풀자
//            }

//            String query = "select m From Member m join fetch m.team"; // fetch join
//
//            List<Member> result = em.createQuery(query, Member.class).getResultList();
//
//            for (Member member : result) {
//                System.out.println("member name = " + member.getUsername() + ", " + member.getTeam().getName());
//            }

            // 컬렉션 페치 조인

//            String query = "select t From Team t join fetch t.members";
//
//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + " | " + team.getMembers().size());
//                for(Member member : team.getMembers()) {
//                    System.out.println("Member : " +  member);
//                }
//            }

            // 중복제거 (중복 안나는데?)
//            String query = "select distinct t From Team t join fetch t.members";
//
//            List<Team> result = em.createQuery(query, Team.class).getResultList();
//
//            System.out.println("result = " +result.size());
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + " | " + team.getMembers().size());
//                for(Member member : team.getMembers()) {
//                    System.out.println("Member : " +  member);
//                }
//            }

            // 페치조인 vs 일반조인
            
            // 일반조인 (연관된 엔티티 한번에 조인 안함)
//            String query = "select t From Team t join t.members m";
//
//            List<Team> result = em.createQuery(query, Team.class)
//                    .getResultList();
//
//            System.out.println("result = " +result.size());
//
//            for (Team team : result) {
//                System.out.println("team = " + team.getName() + " | " + team.getMembers().size());
//                for(Member member : team.getMembers()) {
//                    System.out.println("Member : " +  member);
//                }
//            }
            
            // 페치조인 (연관된 엔티티 함께 조회) (즉시로딩과 비슷)
            // N+1문제 해결하기 좋음
            String query = "select t From Team t join fetch t.members m";

            List<Team> result = em.createQuery(query, Team.class)
                    .getResultList();

            System.out.println("result = " +result.size());

            for (Team team : result) {
                System.out.println("team = " + team.getName() + " | " + team.getMembers().size());
                for(Member member : team.getMembers()) {
                    System.out.println("Member : " +  member);
                }
            }


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