package study.datajpa.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;
import study.datajpa.entity.Team;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@Rollback(false)
class MemberRepositoryTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    TeamRepository teamRepository;

    @PersistenceContext
    EntityManager em;

    @Autowired
    MemberQueryRepository memberQueryRepository;

    @Test
    public void testMember() {
        System.out.println("memberRepository.getClass() = " + memberRepository.getClass());
        // 인터페이스를 보고 spring data jpa가 구현클래스를 만들어서 넣어줌
        Member member = new Member("memberA");
        Member savedMember = memberRepository.save(member);

        Member findMember = memberRepository.findById(savedMember.getId()).get();

        assertThat(findMember.getId()).isEqualTo(member.getId());
        assertThat(findMember.getUsername()).isEqualTo(member.getUsername());
        assertThat(findMember).isEqualTo(member);
    }

    @Test
    public void basicCRUD() {
        Member member1 = new Member("member1");
        Member member2 = new Member("member2");

        memberRepository.save(member1);
        memberRepository.save(member2);

        // 단건 조회 검증
        Member findMember1 = memberRepository.findById(member1.getId()).get();
        Member findMember2 = memberRepository.findById(member2.getId()).get();
        assertThat(findMember1).isEqualTo(member1);
        assertThat(findMember2).isEqualTo(member2);

        /*        findMember1.setUsername("member!!!!!");*/

        // 리스트 조회 검증
        List<Member> all = memberRepository.findAll();
        assertThat(all.size()).isEqualTo(2);

        //카운트 검증
        long count = memberRepository.count();
        assertThat(count).isEqualTo(2);

        //삭제 검증
        memberRepository.delete(member1);
        memberRepository.delete(member2);

        long deletedCount = memberRepository.count();
        assertThat(deletedCount).isEqualTo(0);

    }

    @Test
    public void findByUsernameAndAgeGreaterThen() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("AAA", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByUsernameAndAgeGreaterThan("AAA", 15);

        assertThat(result.get(0).getUsername()).isEqualTo("AAA");
        assertThat(result.get(0).getAge()).isEqualTo(20);
        assertThat(result.size()).isEqualTo(1);
    }

    @Test
    public void findHelloBy() {
        List<Member> helloBy = memberRepository.findTop3HelloBy();
    }

    @Test
    public void testQuery() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findUser("AAA", 10);
        assertThat(result.get(0)).isEqualTo(m1);
    }

    @Test
    public void findUsernameList() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<String> usernameList = memberRepository.findUsernameList();
        for (String s : usernameList) {
            System.out.println("s = " + s);
        }
    }

    @Test
    public void findMemberDto() {
        Team team = new Team("TeamA");
        teamRepository.save(team);

        Member m1 = new Member("AAA", 10);
        m1.setTeam(team);
        memberRepository.save(m1);

        List<MemberDto> memberDto = memberRepository.findMemberDto();
        for (MemberDto dto : memberDto) {
            System.out.println("dto = " + dto);
        }
    }

    @Test
    public void findByNames() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        memberRepository.save(m1);
        memberRepository.save(m2);

        List<Member> result = memberRepository.findByNames(Arrays.asList("AAA", "BBB"));
        for (Member member : result) {
            System.out.println("member = " + member);
        }

    }

    @Test
    public void returnType() {
        Member m1 = new Member("AAA", 10);
        Member m2 = new Member("BBB", 20);
        Member m3 = new Member("CCC", 30);
        Member m4 = new Member("CCC", 40);
        memberRepository.save(m1);
        memberRepository.save(m2);
        memberRepository.save(m3);
        memberRepository.save(m4);

        List<Member> aaa = memberRepository.findListByUsername("AAA");
        Member findMember = memberRepository.findMemberByUsername("AAA");
        System.out.println("findMember = " + findMember);
        Optional<Member> optional = memberRepository.findOptionalByUsername("AAA");
        System.out.println("optional = " + optional);

        // List는 빈 컬렉션을 반환 -> NULL이 아님
        List<Member> result = memberRepository.findListByUsername("ZZZ");
        System.out.println("result = " + result.size());

        // 단건은 NULL을 반환
        Member result2 = memberRepository.findMemberByUsername("ZZZ");
        System.out.println("result2 = " + result2);

        // 데이터 있을지 없을지 모르면 Optional
        Optional<Member> result3 = memberRepository.findOptionalByUsername("ZZZ");
        System.out.println("result3 = " + result3);

        // 여러개 있는 데이터 단건 조회시
        // org.hibernate.NonUniqueResultException: Query did not return a unique result: 2 results were returned

        // 위 예외를 아래 예외로 변환시킴
        // springframework.dao.IncorrectResultSizeDataAccessException: Query did not return a unique result: 2 results were returned
//        Member result4 = memberRepository.findMemberByUsername("CCC");
//        System.out.println("result4 = " + result4);
    }

    @Test
    public void paging() {
        //given
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 10));
        memberRepository.save(new Member("member3", 10));
        memberRepository.save(new Member("member4", 10));
        memberRepository.save(new Member("member5", 10));

        int age = 10;
        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "username"));
        //username 순 DESC 정렬

        //when
        Page<Member> page = memberRepository.findByAge(age, pageRequest);
        // totalCount 알아서 계산

        //DTO로 변환
        Page<MemberDto> toMap = page.map(m -> new MemberDto(m.getId(), m.getUsername(), null));

        // limit + 1 요청
/*        Slice<Member> slice = memberRepository.findByAge(age, pageRequest);*/

        // 이렇게도 가능은 함
/*        List<Member> page = memberRepository.findByAge(age, pageRequest);*/

        // 단순히 3건만 조회
/*        Page<Member> page = memberRepository.findByTop3Age(age);*/

        //페이지 계산 공식 적용...

        //then
        List<Member> content = page.getContent();
/*        List<Member> sliceContent = slice.getContent();*/

        assertThat(content.size()).isEqualTo(3);
        assertThat(page.getTotalElements()).isEqualTo(5);
        assertThat(page.getNumber()).isEqualTo(0); //페이지 번호
        assertThat(page.getTotalPages()).isEqualTo(2); // 총 페이지 개수
        assertThat(page.isFirst()).isTrue(); // 첫페이지인지
        assertThat(page.hasNext()).isTrue(); // 다음 페이지 있는지

/*        assertThat(sliceContent.size()).isEqualTo(3);
//        assertThat(slice.getTotalElements()).isEqualTo(5); // 없음
        assertThat(slice.getNumber()).isEqualTo(0); //페이지 번호
//        assertThat(slice.getTotalPages()).isEqualTo(2); // 없음
        assertThat(slice.isFirst()).isTrue(); // 첫페이지인지
        assertThat(slice.hasNext()).isTrue(); // 다음 페이지 있는지*/

    }

    @Test
    public void bulkUpdate() {
        memberRepository.save(new Member("member1", 10));
        memberRepository.save(new Member("member2", 19));
        memberRepository.save(new Member("member3", 20));
        memberRepository.save(new Member("member4", 21));
        memberRepository.save(new Member("member5", 40));

        //when
        int resultCount = memberRepository.bulkAgePlus(20);
//        em.flush();
//        em.clear();

        // 영속성 컨텍스트를 무시하고 db에 41살로 저장
        // 그런데 findByUsername으로 가져오면 영속성 컨텍스트에서 가져오므로 40살로 나옴
        Member member5 = memberRepository.findByUsername("member5");
        System.out.println("member5 = " + member5);
        // 즉 update 이후에는 영속성컨텍스트 다 날려버려야 함 (em.flush(), em.clear())

        //then
        assertThat(resultCount).isEqualTo(3);
    }

    @Test
    public void findMemberLazy() {
        //given
        //member1 -> teamA
        //member2 -> teamB

        Team teamA = new Team("teamA");
        Team teamB = new Team("teamB");
        teamRepository.save(teamA);
        teamRepository.save(teamB);
        Member member1 = new Member("member1", 10, teamA);
        Member member2 = new Member("member2", 10, teamB);
        Member member3 = new Member("member1", 10, teamB);
        memberRepository.save(member1);
        memberRepository.save(member2);
        memberRepository.save(member3);

        em.flush();
        em.clear();

        //when
        // N+1 발생
/*        List<Member> members = memberRepository.findAll();*/
        // fetchjoin 사용
/*        List<Member> members = memberRepository.findMemberFetchJoin();*/
/*        List<Member> members = memberRepository.findAll();*/

        List<Member> members = memberRepository.findEntityGraphByUsername("member1");

        for (Member member : members) {
            System.out.println("member = " + member.getUsername());
            System.out.println("member.class = " + member.getTeam().getClass()); // team은 프록시로 들어옴
            System.out.println("member.team = " + member.getTeam().getName()); // 지연로딩으로 팀 정보가 필요하면 그때마다 쿼리 날림

        }

    }

    @Test
    public void queryHint() {
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush(); //영속성 컨텍스트 결과를 db랑 동기화
        em.clear(); //영속성 컨텍스트 지움

        //when
/*        Member findMember = memberRepository.findById(member1.getId()).get();
        findMember.setUsername("member2"); // 변경되었으므로 db에 업데이트 쿼리 나감*/

        Member findMember = memberRepository.findReadOnlyByUsername("member1");
        findMember.setUsername("member2");

        em.flush();
    }

    @Test
    public void lock() {
        //given
        Member member1 = new Member("member1", 10);
        memberRepository.save(member1);
        em.flush(); //영속성 컨텍스트 결과를 db랑 동기화
        em.clear(); //영속성 컨텍스트 지움

        //when
        List<Member> result = memberRepository.findLockByUsername("member1");
    }

    @Test
    public void callCustom() {
        List<Member> findMemberCustom = memberRepository.findMemberCustom();
    }

}