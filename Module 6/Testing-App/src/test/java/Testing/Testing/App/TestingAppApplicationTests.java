package Testing.Testing.App;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

//@SpringBootTest
@Slf4j
class TestingAppApplicationTests {
    @BeforeAll
    static void beforeAll() {
        log.info("@BeforeAll");
    }
    @BeforeEach
    void beforeEach() {
        log.info("@BeforeEach");
    }
    @AfterEach
    void afterEach() {
        log.info("@AfterEach");
    }
    @AfterAll
    static void afterAll() {
        log.info("@AfterAll");
    }

	@Test
	void testNumberOne() {
        int a = 1;
        int b = 2;
        int c = addTwoNumbers(a, b);

        //Assertions.assertEquals(3, c);

        Assertions.assertThat(c).isEqualTo(3);
	}
	@Test
	void testNumberTwo() {

	}

    int addTwoNumbers(int a, int b) {
        return a + b;
    }

}
