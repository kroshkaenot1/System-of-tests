package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Question;
import org.springframework.data.repository.CrudRepository;

public interface QuestionRepository extends CrudRepository<Question,Long> {

}
