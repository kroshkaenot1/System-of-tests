package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AnswerRepository extends CrudRepository<Answer,Long> {
    @Query("SELECT a FROM Answer a WHERE a.question_id=?1")
    List<Answer> findByQuestionId(Long id);
    @Query("SELECT a FROM Answer a WHERE a.id=?1")
    Answer findBy_Id(Long id);
    @Query("SELECT a FROM Answer a LEFT JOIN Question q ON q.id = a.question_id LEFT JOIN Test t ON t.id = q.test_id WHERE t.id=?1")
    List<Answer> findAllByTest(Long id);
}
