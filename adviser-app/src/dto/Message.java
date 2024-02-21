package dto;

import java.io.Serializable;

public class Message implements Serializable{
	private String content, timeSent;
	private Boolean opened;
	private User sender, receiver;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Message() {}

	public Message(String content, String timeSent, Boolean opened, User sender, User receiver) {
		super();
		this.content = content;
		this.timeSent = timeSent;
		this.opened = opened;
		this.sender = sender;
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTimeSent() {
		return timeSent;
	}

	public void setTimeSent(String timeSent) {
		this.timeSent = timeSent;
	}

	public Boolean getOpened() {
		return opened;
	}

	public void setOpened(Boolean opened) {
		this.opened = opened;
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
	
}
