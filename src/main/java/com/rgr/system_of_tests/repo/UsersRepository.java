package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.models.Users;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
public interface UsersRepository extends CrudRepository<Users,Long> {

    Users findByUsername(String username);
    @Query("select u from Users u where u.ActivationCode=?1")
    Users findByActivationCode(String code);
    Users findById(String id);
}
