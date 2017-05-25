package com.greenfox.Repository;

import com.greenfox.Modell.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Adam on 2017. 05. 17..
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
