package dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Message implements Serializable{
	private String content, timeSent;
	private Boolean opened;
	private String sender, receiver;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public Message() {}

	public Message(String content, String timeSent, Boolean opened, String sender, String receiver) {
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

	public String getSender() {
		return sender;
	}

	public void setSender(String sender) {
		this.sender = sender;
	}

	public String getReceiver() {
		return receiver;
	}

	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	
	public LocalDateTime getTime() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return LocalDateTime.parse(timeSent, formatter);
	}
}
