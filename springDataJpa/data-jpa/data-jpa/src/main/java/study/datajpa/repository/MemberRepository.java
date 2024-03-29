package study.datajpa.repository;

import jakarta.persistence.LockModeType;
import jakarta.persistence.NamedAttributeNode;
import jakarta.persistence.NamedEntityGraph;
import jakarta.persistence.QueryHint;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import study.datajpa.dto.MemberDto;
import study.datajpa.entity.Member;

import java.util.List;
import java.util.Optional;

// MemberRepositoryCustom도 여기에 확장시켜줌
// JpaSpecificationExecutor : Specification 쓰기 위해(어지간하면 쓰지 말것)
public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom, JpaSpecificationExecutor {

    Member findByUsername(String username);

    List<Member> findByUsernameAndAgeGreaterThan(String username, int age);

    List<Member> findTop3HelloBy();

    // 실무에서 JPQL 많이 쓰는 방식
    @Query("select m from Member m where m.username = :username and m.age = :age")
    List<Member> findUser(@Param("username") String username, @Param("age") int age);

    @Query("select m.username from Member m")
    List<String> findUsernameList();

    @Query("select new study.datajpa.dto.MemberDto(m.id, m.username, t.name) from Member m join m.team t")
    List<MemberDto> findMemberDto();

    @Query("select m from Member m where m.username in :names")
    List<Member> findByNames(@Param("names") List<String> names);

    List<Member> findListByUsername(String username); // 컬렉션
    Member findMemberByUsername(String username); // 단건
    Optional<Member> findOptionalByUsername(String username); // 단건 Optional

/*    @Query(value = "select m from Member m left join m.team t",
        countQuery = "select count(m.username) from Member m")*/
    Page<Member> findByAge(int age, Pageable pageable);
    
    // 조인이 너무 많이 걸려있으면 countQuery를 분리 하는거도 방법

/*    Slice<Member> findByAge(int age, Pageable pageable);*/

    @Modifying(clearAutomatically = true) // executeUpdate()의 역할, clearAutomatically : 업데이트 이후 영속성 컨텍스트 자동으로 날려줌
    @Query("update Member m set m.age = m.age + 1 where m.age >= :age")
    int bulkAgePlus(@Param("age") int age);

    @Query("select m from Member m left join fetch m.team")
    List<Member> findMemberFetchJoin();

    @Override
    @EntityGraph(attributePaths = {"team"})
    List<Member> findAll();

    // 위랑 같음
    @EntityGraph(attributePaths = {"team"})
    @Query("select m from Member m")
    List<Member> findMemberEntityGraph();

//    @EntityGraph(attributePaths = ("team"))
    @EntityGraph("Member.all")
    // 엔티티에서 등록한 @NamedEntityGraph(name = "Member.all", attributeNodes = @NamedAttributeNode("team")) 사용
    List<Member> findEntityGraphByUsername(@Param("username") String username);

    @QueryHints(value = @QueryHint(name = "org.hibernate.readOnly", value = "true") )
    Member findReadOnlyByUsername(String username);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    List<Member> findLockByUsername(String username);

    // List<UsernameOnly> findProjectionsByUsername(@Param("username") String username);
    //List<UsernameOnlyDto> findProjectionsByUsername(@Param("username") String username);
    <T> List<T> findProjectionsByUsername(@Param("username") String username, Class<T> type);

    @Query(value = "select * from member where username = ?", nativeQuery = true)
    Member findByNativeQuery(String username);

    @Query(value = "select m.member_id as id, m.username, t.name as teamName " +
            "from member m left join team t",
            countQuery = "select count(*) from member",
            nativeQuery = true)
    Page<MemberProjection> findByNativeProjection(Pageable pageable);

}
