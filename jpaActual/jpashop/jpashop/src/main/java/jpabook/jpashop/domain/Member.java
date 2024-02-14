package jpabook.jpashop.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Member {

    @Id @GeneratedValue
    @Column(name = "member_id") // db상엔 컬럼명이 이렇게 매핑
    private Long id;

    @NotEmpty // 빈 값 안넘기도록 validation 추가
    private String name;

    @Embedded // 내장 타입이 포함됨
    private Address address;

    @OneToMany(mappedBy = "member") // 일대다 관계 | order 테이블에 있는 member 필드에 의해 매핑되었음
    private List<Order> orders = new ArrayList<>();
}
