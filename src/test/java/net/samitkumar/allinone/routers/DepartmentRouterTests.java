package net.samitkumar.allinone.routers;

import net.samitkumar.allinone.repositories.DepartmentRepository;
import net.samitkumar.allinone.repositories.EmployeeDocumentRepository;
import net.samitkumar.allinone.repositories.EmployeeHistoryRepository;
import net.samitkumar.allinone.repositories.JobTitleRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Testcontainers
@AutoConfigureWebTestClient
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DepartmentRouterTests {
	@Container
	@ServiceConnection
	final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(PostgreSQLContainer.IMAGE)
			.withInitScript("db/schema.sql");


	@Autowired
	WebTestClient webTestClient;

	//This object are not needed but we want to validate some data in the flow
	@Autowired
	DepartmentRepository departmentRepository;
	@Autowired
	JobTitleRepository jobTitleRepository;
	@Autowired
	EmployeeHistoryRepository employeeHistoryRepository;
	@Autowired
	EmployeeDocumentRepository employeeDocumentRepository;

	@Test
	@Order(1)
	void departmentRouterTest() {
		assertAll(
				() -> {
					webTestClient
							.get()
							.uri("/db/department")
							.exchange()
							.expectStatus()
							.isOk()
							.expectBody()
							.json("""
								[]
							""");

				},
				() -> {
					webTestClient
							.post()
							.uri("/db/department")
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue("""
							{
								"departmentName": "IT"
							}
						""")
							.exchange()
							.expectStatus()
							.isOk()
							.expectBody()
							.json("""
							{
								"departmentId": 1,
								"departmentName": "IT"
							}
						""");
				},
				() -> {
					webTestClient
							.get()
							.uri("/db/department/{id}", 1)
							.exchange()
							.expectStatus()
							.isOk()
							.expectBody()
							.json("""
       							 {
									  "departmentId": 1,
									  "departmentName": "IT"
								  }
							""");
				},
				() -> {
					webTestClient
							.put()
							.uri("/db/department/{id}", 1)
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue("""
							{
								"departmentName": "TECHNOLOGY"
							}
							""")
							.exchange()
							.expectStatus()
							.isOk()
							.expectBody()
							.json("""
								{
									"departmentId": 1,
									"departmentName": "TECHNOLOGY"
							  	}
							""");

				},
				() -> {
					webTestClient
							.get()
							.uri("/db/department")
							.exchange()
							.expectBody()
							.json("""
								[
									{
										"departmentId": 1,
										"departmentName": "TECHNOLOGY"
									}
								]
							""");
				},
				() -> {
					webTestClient
							.delete()
							.uri("/db/department/{id}",1)
							.exchange()
							.expectStatus()
							.isOk();
				},
				() -> webTestClient
						.get()
						.uri("/db/department/{id}", 1)
						.exchange()
						.expectStatus()
						.is5xxServerError()
		);
	}

}
