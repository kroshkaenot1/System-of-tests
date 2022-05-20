package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Question;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface QuestionRepository extends CrudRepository<Question,Long> {
    @Query("SELECT q FROM Question q WHERE q.test_id=?1")
    List<Question> findByTestId(long id);
}
