package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) // JPA 작업들은 트랜잭션 안에서 있어야 함
//스프링이 제공하는 Transactional을 쓰는 것을 권장
// (readOnly = true) : 조회에 한정되는 경우, 이를 최적화함

// @AllArgsConstructor // 모든 필드 가지고 생성자 자동으로 만들어줌
@RequiredArgsConstructor // final 붙은 필드만 가지고 생성자 자동으로 만들어줌
public class MemberService {

//    @Autowired // DI
//    private MemberRepository memberRepository;
    //이 경우 autowired 받는 객체를 바꾸질 못함

    private final MemberRepository memberRepository;

    // setter injection
/*    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }*/

    // 테스트 등 할 때 mock 객체들로 바꿀 수 있음
    // 런타임 도중, 객체가 바뀔 가능성 존재
    // 즉 잘 안씀

    // 생성자 injection
    // @Autowired : 생성자가 하나만 있으면 스프링이 자동 주입
    // requiredArgsConstructor 있으므로 아래 코드 필요 없음
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    //회원 가입
    @Transactional // 조회 외의 기능이 있는 경우, 위에 적용한 readonly 적용 안되게 다시 선언
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) { // 중복 회원 검증
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }
    // 같은 이름으로 동시에 가입할 경우 꼬일 수 있음
    // : 중복되지 않아야 하는 필드의 경우 unique 제약조건을 걸어주는게 좋을 수 있음

    // 회원 전체 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }

    @Transactional
    public void update(Long id, String name) {
        Member member = memberRepository.findOne(id);
        member.setName(name);
    }
}
