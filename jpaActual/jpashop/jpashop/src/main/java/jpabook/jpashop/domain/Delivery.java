package jpabook.jpashop.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Delivery {

    @Id @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery", fetch = FetchType.LAZY) //order 테이블에 있는 delivery 필드에 의해 매핑되었음
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING) // Enum 타입임을 명시
    // ORDINAL : 숫자 - 중간에 값이 들어가버리면 이전에 작성된게 다 밀림
    // STRING : 무조건 이거 써야 함
    private DeliveryStatus status; // READY, COMP
}
