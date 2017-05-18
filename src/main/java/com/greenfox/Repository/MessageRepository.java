package com.greenfox.Repository;

import com.greenfox.Modell.Message;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Adam on 2017. 05. 18..
 */
public interface MessageRepository extends CrudRepository<Message, Long> {
}
