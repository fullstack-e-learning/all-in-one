package net.samitkumar.allinone.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.nio.ByteBuffer;

@Table("employee_documents")
public record EmployeeDocument(
        @Id Integer documentId,
        String documentName,
        byte[] documentContent,
        Integer employeeId) {
}
