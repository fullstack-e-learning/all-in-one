package net.samitkumar.allinoneapi.handlers;

import lombok.RequiredArgsConstructor;
import net.samitkumar.allinoneapi.models.Employee;
import net.samitkumar.allinoneapi.repositories.EmployeeRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import static java.util.Objects.nonNull;

@Component
@RequiredArgsConstructor
public class EmployeeHandler {
    final EmployeeRepository employeeRepository;
    public Mono<ServerResponse> allEmployee(ServerRequest request) {
        return ServerResponse
                .ok()
                .bodyValue(employeeRepository.findAll());
    }

    public Mono<ServerResponse> employeeById(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariable("id"));
        return Mono.fromCallable(() -> employeeRepository.findById(id).orElseThrow())
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> newEmployee(ServerRequest request) {
        return request
                .bodyToMono(Employee.class)
                .map(employeeRepository::save)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> updateEmployee(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariable("id"));
        return request
                .bodyToMono(Employee.class)
                .zipWith(Mono.fromCallable(() -> employeeRepository.findById(id).orElseThrow()), (newEmp, existingEmp) -> {
                    return new Employee(
                            existingEmp.employeeId(),
                            newEmp.firstName(),
                            newEmp.lastName(),
                            newEmp.email(),
                            newEmp.phoneNumber(),
                            newEmp.hireDate(),
                            newEmp.jobId(),
                            newEmp.salary(),
                            newEmp.managerId(),
                            newEmp.departmentId(),
                            nonNull(newEmp.employeeHistory())? newEmp.employeeHistory() : existingEmp.employeeHistory(),
                            nonNull(newEmp.employeeDocuments()) ? newEmp.employeeDocuments() : existingEmp.employeeDocuments());
                })
                .map(employeeRepository::save)
                .flatMap(ServerResponse.ok()::bodyValue);
    }

    public Mono<ServerResponse> deleteEmployee(ServerRequest request) {
        var id = Integer.parseInt(request.pathVariable("id"));
        return Mono.fromRunnable(() -> employeeRepository.deleteById(id))
                .then(ServerResponse.ok().build());
    }
}
