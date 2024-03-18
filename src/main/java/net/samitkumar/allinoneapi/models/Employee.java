package net.samitkumar.allinoneapi.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Set;

@Table("employees")
public record Employee(
        @Id Integer employeeId,
        String firstName,
        String lastName,
        String email,
        String phoneNumber,
        @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate hireDate,
        Integer jobId,
        Double salary,
        Integer managerId,
        Integer departmentId,
        @MappedCollection(idColumn = "employee_id") EmployeeHistory employeeHistory,
        @MappedCollection(idColumn = "employee_id") Set<EmployeeDocument> employeeDocuments) {
}
