package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuestionRepository extends CrudRepository<Question,Long> {
    @Query("SELECT q FROM Question q WHERE q.test_id=?1")
    List<Question> findByTestId(long id);
    @Query("SELECT q FROM Question q WHERE q.question_text=?1")
    Question findByQuestion(String question);
}
