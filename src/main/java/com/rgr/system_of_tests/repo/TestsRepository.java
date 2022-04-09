package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.models.Tests;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public interface TestsRepository extends CrudRepository<Tests,Long> {
    @Query(value = "SELECT t FROM Tests t WHERE DATE(t.date) =?1")
    ArrayList<Tests> findByDate(Date date);
}
