package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;

@Embeddable // 내장타입
@Getter // 값 타입이므로 변경 불가능하게 -> setter 없앰
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    }

    public Address(String city, String street, String zipcode) { // 생성자 통해서만 값 입력
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
