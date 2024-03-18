package net.samitkumar.allinoneapi;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@SpringBootTest
@Testcontainers
@AutoConfigureWebTestClient
class AllInOneApiApplicationTests {
	@Container
	@ServiceConnection
	final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(PostgreSQLContainer.IMAGE)
			.withInitScript("db/schema.sql");

	@Test
	void contextLoads() {
	}

}
