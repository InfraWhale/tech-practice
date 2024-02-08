package jpabook.jpashop.controller;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MemberForm { // validation 등 추가 위해 MemberForm을 추가, 실무에선 엔티티보다 오히려 이 방식을 더 많이 씀

    @NotEmpty(message = "회원 이름은 필수 입니다") // 빈값 막기
    private String name;
    private String city;
    private String street;
    private String zipcode;

}
