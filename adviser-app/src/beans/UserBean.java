package beans;

import java.util.List;

import org.json.JSONObject;

import dao.MessageDAO;
import dao.UserDAO;
import dto.Message;
import dto.User;
import util.LoginResult;

public class UserBean {
	private User user;
	private boolean isLoggedIn;
	private UserDAO userDAO;
	private MessageDAO messageDAO;
	
	public UserBean() {
		userDAO = new UserDAO();
		messageDAO = new MessageDAO();
	}
	
	public LoginResult login(String username, String password) {
		if (!userDAO.userExists(username))
			return LoginResult.NO_USER;
		user = userDAO.login(username, password);
		if (user == null)
			return LoginResult.WRONG_PASSWORD;
		else if (!"S".equals(user.getUserType()))
				return LoginResult.WRONG_USERTYPE;
		else {
			isLoggedIn = true;
			return LoginResult.SUCCESS;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}
	
	public List<Message> getMessages() {
		return messageDAO.getAll(user.getUsername());
	}
	
	public List<Message> getMessages(String username) {
		return messageDAO.getAll(username);
	}
	
	public void openMessage(JSONObject data) {
		messageDAO.openMessage(data.getString("sender"), data.getString("receiver"), data.getString("timeSent"));
	}
	
	public User getUser(String username) {
		return userDAO.get(username);
	}
	
	public Message getMessage(JSONObject data) {
		return messageDAO.get(data.getString("sender"), data.getString("receiver"), data.getString("timeSent"));
	}
}
