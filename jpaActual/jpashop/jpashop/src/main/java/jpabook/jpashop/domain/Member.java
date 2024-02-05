package jpabook.jpashop.domain;

import jakarta.persistence.*;
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

    private String name;

    @Embedded // 내장 타입이 포함됨
    private Address address;

    @OneToMany(mappedBy = "member") // 일대다 관계 | order 테이블에 있는 member 필드에 의해 매핑되었음
    private List<Order> orders = new ArrayList<>();
}
