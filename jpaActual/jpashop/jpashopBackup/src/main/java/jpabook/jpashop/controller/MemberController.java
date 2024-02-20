package jpabook.jpashop.controller;

import jakarta.persistence.PersistenceUnit;
import jakarta.validation.Valid;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/members/new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new MemberForm());
        return "members/createMemberForm";
    }

    @PostMapping("/members/new")
    public String create(@Valid MemberForm form, BindingResult result) { //@Valid :  @NotEmpty 등등을 검증
        // BindingResult : 오류가 발생 시, 이곳에 담기고 아래 코드는 실행됨

        if (result.hasErrors()) {
            return "members/createMemberForm"; // BindingResult를 spring이 thymeleaf로 보내줌 (이미 integration 되어있음)
        }

        Address address = new Address(form.getCity(), form.getStreet(), form.getZipcode());

        Member member = new Member();
        member.setName(form.getName());
        member.setAddress(address);

        memberService.join(member);
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model) {
        model.addAttribute("members", memberService.findMembers());
        return "members/memberList";
    }
}
