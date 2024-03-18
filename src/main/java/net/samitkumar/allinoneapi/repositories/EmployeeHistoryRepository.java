package net.samitkumar.allinoneapi.repositories;

import net.samitkumar.allinoneapi.models.EmployeeHistory;
import org.springframework.data.repository.ListCrudRepository;

public interface EmployeeHistoryRepository extends ListCrudRepository<EmployeeHistory,Integer> {
}
