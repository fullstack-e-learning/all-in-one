package net.samitkumar.allinoneapi.handlers;

import lombok.RequiredArgsConstructor;
import net.samitkumar.allinoneapi.repositories.EmployeeDocumentRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class EmployeeDocumentsHandler {
    final EmployeeDocumentRepository employeeDocumentRepository;

    public Mono<ServerResponse> allEmployeeDocuments(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> employeeDocumentsbyId(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> newEmployeeDocument(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> updateEmployeeDocument(ServerRequest request) {
        return null;
    }

    public Mono<ServerResponse> deleteEmployeeDocument(ServerRequest request) {
        return null;
    }
}
