import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitTest {
    @DisplayName("1+2는 3이다") // 테스트 이름
    @Test // 테스트 메서드
    public void junitTest() {
        int a = 1;
        int b = 2;
        int sum = 3;

        // Assertions.assertEquals(a + b, sum); // 값이 같은지 확인

        assertThat(a + b).isEqualTo(sum); // assertJ 사용
    }

/*    @DisplayName("1+3는 4이다") // 테스트 이름
    @Test // 테스트 메서드
    public void junitFailedTest() {
        int a = 1;
        int b = 3;
        int sum = 3;

        Assertions.assertEquals(a + b, sum); // 실패하는 케이스
    }*/
}