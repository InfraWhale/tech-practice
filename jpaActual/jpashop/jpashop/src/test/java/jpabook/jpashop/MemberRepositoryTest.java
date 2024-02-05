package jpabook.jpashop;

import jpabook.jpashop.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringRunner.class) // junit한테 스프링 관련 테스트를 함을 알려줌
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired MemberRepository memberRepository; // Member Repository 주입받음

    @Test
    @Transactional // entityManager를 통한 모든 데이터 변경은 항상 Transaction 안에서 이루어져야 함 / spring패키지꺼를 쓰는것을 권장함
    @Rollback(false) // 테스트 코드의 경우 한 트랜잭션 후 자동 롤백, 데이터가 들어가는것을 확인하려면 false 지정 가능
    public void testMember() throws Exception {
        //given
        Member member = new Member();
        member.setUsername("memberA");

        //when
        Long savedId = memberRepository.save(member); //ctrl + alt + v
        Member findMember = memberRepository.find(savedId);

        //then
        Assertions.assertThat(findMember.getId()).isEqualTo(member.getId());
        Assertions.assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        Assertions.assertThat(findMember).isEqualTo(member); // 같은 영속성 컨텍스트 안에서 id값 같으면 둘은 같음
        System.out.println("findMember == member: " + (findMember == member) );


    }
}