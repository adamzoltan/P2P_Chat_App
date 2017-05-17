package com.greenfox.Repository;

import com.greenfox.Modell.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adam on 2017. 05. 17..
 */
public interface UserRepository extends CrudRepository<User, Integer> {
}
