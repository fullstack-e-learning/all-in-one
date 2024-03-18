package net.samitkumar.allinone.repositories;

import net.samitkumar.allinone.models.Employee;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeRepository extends ListCrudRepository<Employee, Integer> {
}
