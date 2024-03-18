package net.samitkumar.allinone.repositories;

import net.samitkumar.allinone.models.Department;
import org.springframework.data.repository.ListCrudRepository;

public interface DepartmentRepository extends ListCrudRepository<Department, Integer> {
}
