package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
public interface UsersRepository extends CrudRepository<User,Long> {

    User findByUsername(String username);

    @Query("select u from User u where u.ActivationCode=?1")
    User findByActivationCode(String code);
    @Query("select i from User i where i.id=?1")
    User findId(Long id);
    @Query("SELECT  u FROM User u where u.username=?1")
    User findByName(String name);
}
