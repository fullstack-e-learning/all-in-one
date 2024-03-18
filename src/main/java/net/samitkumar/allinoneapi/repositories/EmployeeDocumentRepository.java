package net.samitkumar.allinoneapi.repositories;

import net.samitkumar.allinoneapi.models.EmployeeDocument;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeDocumentRepository extends ListCrudRepository<EmployeeDocument, Integer> {
}
