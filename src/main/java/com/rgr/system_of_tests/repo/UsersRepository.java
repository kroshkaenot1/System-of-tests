package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.models.Users;
import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<Users,Long> {

    Users findByUsername(String username);
}
