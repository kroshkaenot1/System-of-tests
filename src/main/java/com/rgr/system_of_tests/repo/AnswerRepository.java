package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer,Long> {
    @Query("SELECT a FROM Answer a WHERE a.question_id=?1")
    List<Answer> findId(long id);
}
