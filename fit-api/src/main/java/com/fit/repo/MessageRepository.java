package com.fit.repo;

import java.time.LocalDateTime;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fit.model.Message;
import com.fit.model.MessageId;
import com.fit.model.User;

public interface MessageRepository extends JpaRepository<Message, MessageId>{
	Message findBySenderAndReceiverAndTimeSent(User sender, User receiver, LocalDateTime timeSent);
}
