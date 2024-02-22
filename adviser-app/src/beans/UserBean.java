package beans;

import dao.UserDAO;
import dto.User;
import util.LoginResult;

public class UserBean {
	private User user;
	private boolean isLoggedIn;
	private UserDAO userDAO;
	
	public UserBean() {
		userDAO = new UserDAO();
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
}
