package jpabook.jpashop;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication // 해당 어노테이션이 있는 패키지, 하위 패키지 모두 컴포넌트 스캔의 대상으로 함
public class JpashopApplication {
	public static void main(String[] args) {
		SpringApplication.run(JpashopApplication.class, args);
	}

	@Bean
	Hibernate6Module hibernate6Module() {
		Hibernate6Module hibernate6Module = new Hibernate6Module();
		hibernate6Module.configure(Hibernate6Module.Feature.FORCE_LAZY_LOADING, true);
		return hibernate6Module;
	}
}
