package study.datajpa.dto;

import lombok.Data;
import study.datajpa.entity.Member;

@Data
public class MemberDto {

    private Long id;
    private String username;
    private String teamName;

    public MemberDto(Long id, String username, String teamName) {
        this.id = id;
        this.username = username;
        this.teamName = teamName;
    }
    
    // 엔티티 받아서 생성자만들기
    public MemberDto(Member member) {
        this.id = member.getId();
        this.username = member.getUsername();
    }
}
