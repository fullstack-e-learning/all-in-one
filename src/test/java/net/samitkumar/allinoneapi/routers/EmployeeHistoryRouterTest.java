package net.samitkumar.allinoneapi.routers;

import net.samitkumar.allinoneapi.models.Department;
import net.samitkumar.allinoneapi.models.JobTitle;
import net.samitkumar.allinoneapi.repositories.DepartmentRepository;
import net.samitkumar.allinoneapi.repositories.JobTitleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.assertAll;

@SpringBootTest
@Testcontainers
@AutoConfigureWebTestClient
public class EmployeeHistoryRouterTest {

    @Container
    @ServiceConnection
    final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(PostgreSQLContainer.IMAGE)
            .withInitScript("db/schema.sql");


    @Autowired
    WebTestClient webTestClient;

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    JobTitleRepository jobTitleRepository;

    @Test
    void employeeHistoryTest() {
        var dept = departmentRepository
                .save(
                        new Department(null, "IT")
                );

        var job = jobTitleRepository
                .save(
                        new JobTitle(null,"Engineer", 2000.0, 4000.00)
                );

        assertAll(
                () -> webTestClient
                        .get()
                        .uri("/db/employee/history")
                        .exchange()
                        .expectStatus()
                        .isOk()
                        .expectBody()
                        .json("""
                            []
                        """)
        );
    }
}
