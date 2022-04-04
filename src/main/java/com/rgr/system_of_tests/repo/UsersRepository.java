package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.models.Users;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UsersRepository extends CrudRepository<Users,Long> {

    Users findByUsername(String username);
}
