package com.greenfox.Repository;

import com.greenfox.Modell.Message;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Adam on 2017. 05. 18..
 */
@Repository
public interface MessageRepository extends CrudRepository<Message, Long> {
   List<Message> findAllByOrderByTimestampAsc();
}
