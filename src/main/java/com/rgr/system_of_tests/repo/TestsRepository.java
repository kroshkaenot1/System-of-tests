package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
@Repository
public interface TestsRepository extends CrudRepository<Test,Long> {
    @Query("SELECT t FROM Test t WHERE DATE(t.date) =?1")
    ArrayList<Test> findByDate(Date date);
    @Query("SELECT t FROM Test t WHERE t.id=?1")
    Test findId(Long id);
}
