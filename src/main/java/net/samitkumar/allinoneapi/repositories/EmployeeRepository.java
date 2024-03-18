package net.samitkumar.allinoneapi.repositories;

import net.samitkumar.allinoneapi.models.Employee;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeRepository extends ListCrudRepository<Employee, Integer> {
}
