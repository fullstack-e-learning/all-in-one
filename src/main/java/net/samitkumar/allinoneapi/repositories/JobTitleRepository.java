package net.samitkumar.allinoneapi.repositories;

import net.samitkumar.allinoneapi.models.JobTitle;
import org.springframework.data.repository.ListCrudRepository;

public interface JobTitleRepository extends ListCrudRepository<JobTitle, Integer> {
}
