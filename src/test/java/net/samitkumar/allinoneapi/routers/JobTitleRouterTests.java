package net.samitkumar.allinoneapi.routers;

import net.samitkumar.allinoneapi.repositories.DepartmentRepository;
import net.samitkumar.allinoneapi.repositories.EmployeeDocumentRepository;
import net.samitkumar.allinoneapi.repositories.EmployeeHistoryRepository;
import net.samitkumar.allinoneapi.repositories.JobTitleRepository;
import org.junit.jupiter.api.Test;
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
class JobTitleRouterTests {
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
	void jobTitleRouterTest() {
		assertAll(
				//all
				() -> webTestClient
						.get()
						.uri("/db/jobtitle")
						.exchange()
						.expectStatus()
						.isOk()
						.expectBody()
						.json("""
      						[]
						"""),
				//save
				() -> webTestClient
						.post()
						.uri("/db/jobtitle")
						.contentType(MediaType.APPLICATION_JSON)
						.bodyValue("""
							{
								"title" : "Engineer",
								"minSalary": 4000.00,
								"maxSalary": 6000.00
							}
						""")
						.exchange()
						.expectStatus()
						.isOk()
						.expectBody()
						.json("""
							{
								"jobId": 1,
								"title" : "Engineer",
								"minSalary": 4000.00,
								"maxSalary": 6000.00
							}
						"""),
				//find by Id
				() -> webTestClient
						.get()
						.uri("/db/jobtitle/{id}", 1)
						.exchange()
						.expectStatus()
						.isOk()
						.expectBody()
						.json("""
							{
								"jobId": 1,
								"title" : "Engineer",
								"minSalary": 4000.00,
								"maxSalary": 6000.00
							}
						"""),
				//Update
				() -> webTestClient
						.put()
						.uri("/db/jobtitle/{id}", 1)
						.contentType(MediaType.APPLICATION_JSON)
						.bodyValue("""
							{
								"title" : "Developer",
								"minSalary": 4000.00,
								"maxSalary": 6000.00
							}
						""")
						.exchange()
						.expectStatus()
						.isOk()
						.expectBody()
						.json("""
							{
								"jobId": 1,
								"title" : "Developer",
								"minSalary": 4000.00,
								"maxSalary": 6000.00
							}
						"""),
				//delete
				() -> webTestClient
						.delete()
						.uri("/db/jobtitle/{id}", 1)
						.exchange()
						.expectStatus()
						.isOk(),
				//Find by id and check If we have no data
				() -> webTestClient
						.get()
						.uri("/db/jobtitle/{id}", 1)
						.exchange()
						.expectStatus()
						.is5xxServerError()

		);
	}

}
