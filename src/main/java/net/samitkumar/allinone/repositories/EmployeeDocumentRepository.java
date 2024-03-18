package net.samitkumar.allinone.repositories;

import net.samitkumar.allinone.models.EmployeeDocument;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeDocumentRepository extends ListCrudRepository<EmployeeDocument, Integer> {
}
