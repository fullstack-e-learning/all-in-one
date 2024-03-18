package net.samitkumar.allinoneapi.repositories;

import net.samitkumar.allinoneapi.models.Department;
import org.springframework.data.repository.ListCrudRepository;

public interface DepartmentRepository extends ListCrudRepository<Department, Integer> {
}
