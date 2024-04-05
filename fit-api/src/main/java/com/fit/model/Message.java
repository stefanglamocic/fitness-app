package com.fit.model;

import java.time.LocalDateTime;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonFilter;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@JsonFilter("messageFilter")
@Entity
public class Message {
	@ManyToOne
	@JoinColumn(name = "sender", 
			referencedColumnName = "username")
	private User sender;
	
	@ManyToOne
	@JoinColumn(name = "receiver", 
			referencedColumnName = "username")
	private User receiver;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private LocalDateTime timeSent;
	private String content;
	private Boolean opened;
	
	public Message() {
		super();
	}

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public LocalDateTime getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(LocalDateTime timeSent) {
		this.timeSent = timeSent;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
	}

	@Override
	public int hashCode() {
		return Objects.hash(receiver, sender, timeSent);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Message other = (Message) obj;
		return Objects.equals(receiver, other.receiver) && Objects.equals(sender, other.sender)
				&& Objects.equals(timeSent, other.timeSent);
	}
}
