package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;
    // // private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    // private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    // 할인정책이 변경되었다.

    // 현재 추상클래스 뿐만 아니라 구현클래스인 FixDiscountPolicy도 의존중 -> DIP 위반
    // 그래서 RateDiscountPolicy를 쓰고싶다면, 코드를 변경해야 한다. -> OCP 위반

    // 그래서 어떻게? 이렇게 한다.

    private final DiscountPolicy discountPolicy;

    // 단 이렇게 하면 NPE 발생한다.


    // @Autowired // 중요 생성자 단 한개면 Autowired 생략가능
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        // 단일책임원칙을 잘 지킨 예시 : 수정할일 있으면 discountPolicy 부분에서 수정하면 된다.
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }

    // 테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
