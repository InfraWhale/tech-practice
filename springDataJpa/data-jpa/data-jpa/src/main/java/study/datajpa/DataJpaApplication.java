package study.datajpa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
// @EnableJpaAuditing(modifyOnCreate = false) 이렇게 하면 create 할때 update 컬럼에 값 null (권장 x)
@SpringBootApplication
// @EnableJpaRepositories(basePackages = "경로") // Springboot가 알아서 잡아준다 -> 필요없음
public class DataJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataJpaApplication.class, args);
	}

	// spring security 등에서 securityContext에서 세션 정보를 꺼내와야 함
	// 다른 경우라면 http 세션에서 꺼내거나
	// UUID.randomUUID().toString() 부분에 유저 아이디를 가져와서 넣어줌
	@Bean
		public AuditorAware<String> auditorProvider() {
		return () -> Optional.of(UUID.randomUUID().toString());
	}
	// 이렇게 하면 Auditing 컬럼에 자동으로 입력한 사람, 수정한 사람 넣어줌

/*	public AuditorAware<String> auditorProvider() { // 풀어쓴 경우
		return new AuditorAware<String>() {
			@Override
			public Optional<String> getCurrentAuditor() {
				return Optional.of(UUID.randomUUID().toString());
			}
		};
	}*/

}
