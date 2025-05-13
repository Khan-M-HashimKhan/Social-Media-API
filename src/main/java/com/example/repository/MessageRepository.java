package com.example.repository;
import org.springframework.data.jpa.repository.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.support.JpaRepositoryImplementation;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepositoryImplementation<Message,Long> {

    boolean existsByMessageId(Integer mid);
    Message findByMessageId(Integer mid);
    
    @Transactional
    int deleteByMessageId(Integer mid);

    @Modifying
    @Transactional
    @Query("Update Message m SET m.messageText = :text WHERE m.messageId = :mid")
    int updateTextById(@Param("mid") Integer mid, @Param("text") String text);

    List<Message> findByPostedBy(Integer uID);
}
