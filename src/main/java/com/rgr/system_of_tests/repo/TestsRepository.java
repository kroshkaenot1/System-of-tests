package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.models.Tests;
import org.springframework.data.repository.CrudRepository;

public interface TestsRepository extends CrudRepository<Tests,Long> {

}
