package org.farpost.farpostapi2.repositories;

import org.farpost.farpostapi2.enitities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    User findUserByUsername(String username);

}
