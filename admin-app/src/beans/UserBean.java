package beans;

import dao.IUserDAO;
import dto.User;

public class UserBean {
	private User user;
	private boolean isLoggedIn = false;
	private IUserDAO userDAO = null;
	
	public UserBean(IUserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	public LoginResult login(String username, String password) {
		if (!userDAO.usernameExists(username))
			return LoginResult.USERNAME_NOT_FOUND;
		
		user = userDAO.login(username, password);
		
		if (user == null)
			return LoginResult.WRONG_PASSWORD;
		else if (!user.getActivated())
			return LoginResult.NOT_ACTIVATED;
		else if(!isAdmin())
			return LoginResult.NOT_ADMIN;
			
		isLoggedIn = true;
		return LoginResult.SUCCESS;
	}
	
	public void logout() {
		isLoggedIn = false;
		user = null;
	}
	
	public boolean isAdmin() {
		if (user == null)
			return false;
		return user.getUserType().equals("A");
	}
	
	public User getUser() {
		return user;
	}
	
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	
	
}
