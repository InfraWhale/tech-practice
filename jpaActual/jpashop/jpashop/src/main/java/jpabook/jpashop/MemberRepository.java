package jpabook.jpashop;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jpabook.jpashop.domain.Member;
import org.springframework.stereotype.Repository;

@Repository
public class MemberRepository {

    @PersistenceContext // EntityManager 얘가 주입해줌
    private EntityManager em;

    public Long save(Member member) {
        em.persist(member);
        return member.getId(); // 커맨드와 쿼리 분리(member 객체 바로 가져오지 말고 id 정도만 들고오게)
    }

    public Member find(Long id) {
        return em.find(Member.class, id);
    }
}
