package io.wiiisdom.api;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(locations = "classpath:application-dev.yml")
class ApiApplicationTests {

	@Test
	void contextLoads() {
	}

}
