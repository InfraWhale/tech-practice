package jpabook.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model; // 이 모델이란 것에 데이터를 넣고 뷰에 넘길 수 있음
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")
    public String hello(Model model) {
        model.addAttribute("data", "hello!!!"); // 뷰의 ${data}로 값을 넣어줌
        return "hello"; // 결과를 hello.html로 보내줌
    }
}
