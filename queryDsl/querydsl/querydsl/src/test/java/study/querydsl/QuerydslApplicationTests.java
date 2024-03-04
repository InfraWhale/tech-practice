package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.controller.entity.Hello;
import study.querydsl.controller.entity.QHello;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@Autowired
	EntityManager em;

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		JPAQueryFactory query = new JPAQueryFactory(em);
		//QHello qHello = new QHello("h");
		QHello qHello = QHello.hello;


		Hello result = query
				.selectFrom(qHello)
				.fetchOne();

		Assertions.assertThat(result).isEqualTo(hello);
		Assertions.assertThat(result.getId()).isEqualTo(hello.getId());
	}

}
