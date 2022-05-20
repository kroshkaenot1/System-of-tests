package com.rgr.system_of_tests.repo;

import com.rgr.system_of_tests.repo.models.Invitation;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvitationRepository extends CrudRepository<Invitation,Long> {
    @Query("SELECT i FROM Invitation i where i.test_id=?1 and i.user_id=?2")
    Invitation findId(Long id,Long user_id);
}
