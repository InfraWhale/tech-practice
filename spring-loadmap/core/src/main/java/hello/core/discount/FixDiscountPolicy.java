package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.springframework.stereotype.Component;

// @Component : 둘다 컴포넌트로 등록해버리면 (Fix, Rate) :
// org.springframework.beans.factory.UnsatisfiedDependencyException: Error creating bean with
// name 'orderServiceImpl' defined in file [C:\Users\gyqja\git\tech-practice\spring-loadmap\core\out\production\classes\hello\core\order\OrderServiceImpl.class]:
// Unsatisfied dependency expressed through constructor parameter 1: No qualifying bean of type 'hello.core.discount.DiscountPolicy'
// available: expected single matching bean but found 2: fixDiscountPolicy,rateDiscountPolicy
public class FixDiscountPolicy implements DiscountPolicy {

    private int discountFixAmount = 1000; // 1000원 할인


    @Override
    public int discount(Member member, int price) {
        if(member.getGrade() == Grade.VIP) {
            return discountFixAmount;
        } else {
            return 0;
        }
    }
}
