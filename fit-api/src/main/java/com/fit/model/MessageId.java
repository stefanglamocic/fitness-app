package com.fit.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

public class MessageId implements Serializable{

	private static final long serialVersionUID = 1L;
	
	private User sender;
    private User receiver;
    private LocalDateTime timeSent;
    
	public MessageId() {
		super();
		// TODO Auto-generated constructor stub
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
		MessageId other = (MessageId) obj;
		return Objects.equals(receiver, other.receiver) && Objects.equals(sender, other.sender)
				&& Objects.equals(timeSent, other.timeSent);
	}
    
    

}
