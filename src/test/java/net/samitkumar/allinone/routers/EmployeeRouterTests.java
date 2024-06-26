package net.samitkumar.allinone.routers;

import net.samitkumar.allinone.models.Department;
import net.samitkumar.allinone.models.JobTitle;
import net.samitkumar.allinone.repositories.DepartmentRepository;
import net.samitkumar.allinone.repositories.EmployeeDocumentRepository;
import net.samitkumar.allinone.repositories.EmployeeHistoryRepository;
import net.samitkumar.allinone.repositories.JobTitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest(properties = "spring.flyway.enabled=false")
@Testcontainers
@AutoConfigureWebTestClient
@WithMockUser
class EmployeeRouterTests {
	@Container
	@ServiceConnection
	final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DockerImageName.parse("postgres:15"))
			.withInitScript("db/migration/V1__schema.sql");


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
	void employeeRouterTest() {
		var dept = departmentRepository
				.save(
						new Department(null, "IT")
				);

		var job = jobTitleRepository
				.save(
						new JobTitle(null,"Engineer", 2000.0, 4000.00)
				);

		System.out.println(jobTitleRepository.findAll());
		System.out.println(departmentRepository.findAll());

		assertAll(
				//find all
				() -> {
					webTestClient
							.get()
							.uri("/db/employee")
							.exchange()
							.expectStatus()
							.isOk()
							.expectBody()
							.json("""
								[]
							""");
				},
				//save
				() -> {
					webTestClient
							.post()
							.uri("/db/employee")
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue("""
								{
									"firstName": "John",
									"lastName": "Doe",
									"email": "john.doe@allinone.net",
									"phoneNumber": "1234567",
									"hireDate": "2024-02-01",
									"jobId": 1,
									"salary": 200.00,
									"managerId": null,
									"departmentId": 1,
									"employeeHistory": {
										"startDate": "2024-03-15",
										"jobId": 1,
										"departmentId": 1
									},
									"employeeDocuments": [
										{
											"documentName": "Id proof",
											"documentContent": [72, 101, 108, 108, 111 ]
										}
									]
								}
								""")
							.exchange()
							.expectStatus()
							.isOk()
							.expectBody()
							.json("""
								{
									"employeeId":1,
									"firstName":"John",
									"lastName":"Doe",
									"email":"john.doe@allinone.net",
									"phoneNumber":"1234567",
									"hireDate":"2024-02-01",
									"jobId":1,
									"salary":200.0,
									"managerId":null,
									"departmentId":1,
									"employeeHistory": {
										"historyId":1,
										"startDate":"2024-03-15",
										"endDate":null,
										"jobId":1,
										"departmentId":1
									},
									"employeeDocuments": [
										{
											"documentId":1,
											"documentName":"Id proof",
											"documentContent":"SGVsbG8="
										}
									]
								}
					""");
				},
				//employee byId
				() -> {
					webTestClient
							.get()
							.uri("/db/employee/{id}", 1)
							.exchange()
							.expectStatus()
							.isOk()
							.expectBody()
							.json("""
								{
									"employeeId":1,
									"firstName":"John",
									"lastName":"Doe",
									"email":"john.doe@allinone.net",
									"phoneNumber":"1234567",
									"hireDate":"2024-02-01",
									"jobId":1,
									"salary":200.0,
									"managerId":null,
									"departmentId":1,
									"employeeHistory": {
										"historyId":1,
										"startDate":"2024-03-15",
										"endDate":null,
										"jobId":1,
										"departmentId":1
									},
									"employeeDocuments": [
										{
											"documentId":1,
											"documentName":"Id proof",
											"documentContent":"SGVsbG8="
										}
									]
								}
								""");
				},
				//update
				() -> {
					webTestClient
							.put()
							.uri("/db/employee/{id}", 1)
							.contentType(MediaType.APPLICATION_JSON)
							.bodyValue("""
								{
									"firstName": "John K",
									"lastName": "Doe",
									"email": "johnk.doe@allinone.net",
									"phoneNumber": "1234567",
									"hireDate": "2024-02-15",
									"jobId": 1,
									"salary": 200.00,
									"managerId": null,
									"departmentId": 1
								}
							""")
							.exchange()
							.expectStatus()
							.isOk()
							.expectBody()
							.json("""
								{
									"employeeId":1,
									"firstName": "John K",
									"lastName": "Doe",
									"email": "johnk.doe@allinone.net",
									"phoneNumber": "1234567",
									"hireDate": "2024-02-15",
									"jobId": 1,
									"salary": 200.00,
									"managerId": null,
									"departmentId": 1
								}
							""");
				},
				//make sure reference table has not impacted
				() -> {
					assertThat(employeeDocumentRepository.findAll())
							.asList()
							.size()
							.isEqualTo(1);

					assertThat(employeeHistoryRepository.findAll())
							.asList()
							.size()
							.isEqualTo(1);

				},
				//delete employee
				() -> webTestClient
						.delete()
						.uri("/db/employee/{id}", 1)
						.exchange()
						.expectStatus()
						.isOk(),
				//make sure the reference data are also deleted
				() -> {
					assertThat(employeeDocumentRepository.findAll())
							.asList()
							.size()
							.isEqualTo(0);

					assertThat(employeeHistoryRepository.findAll())
							.asList()
							.size()
							.isEqualTo(0);

				}
		);
	}

}
