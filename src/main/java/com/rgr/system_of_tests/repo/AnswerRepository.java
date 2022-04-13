package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Answer;
import org.springframework.data.repository.CrudRepository;

public interface AnswerRepository extends CrudRepository<Answer,Long> {
}
