package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;

public interface TestsRepository extends CrudRepository<Test,Long> {
    @Query("SELECT t FROM Test t WHERE DATE(t.date) =?1")
    ArrayList<Test> findByDate(Date date);
    @Query("SELECT t FROM Test t WHERE t.id=?1")
    Test findId(Long id);
}
