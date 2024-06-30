package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// @Controller -> 뷰 네임을 반환
// @RestController -> String을 바로 반환 (Rest API에 사용)
@RestController
@Slf4j
public class LogTestController {

    // 위의 @Slf4j 쓰면 안써도 됨
    //private final Logger log = LoggerFactory.getLogger(getClass());
    //private final Logger log = LoggerFactory.getLogger(LogTestController.class);

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);
        // name = Spring

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info("info log={}", name);
        // 2024-06-30T23:42:25.332+09:00  INFO 18252 --- [springmvc] [nio-8080-exec-2] h.springmvc.basic.LogTestController      : info log=Spring
        log.warn("warn log={}", name);
        log.error("error log={}", name);

        return "ok";
    }
}
