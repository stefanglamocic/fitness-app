package beans;

import java.util.List;

import dao.IUserDAO;
import dto.User;

public class UserBean {
	private User user;
	private boolean isLoggedIn = false;
	private IUserDAO userDAO = null;
	private CategoryBean categoryBean;
	
	public UserBean() {}
	
	public UserBean(IUserDAO userDAO, CategoryBean categoryBean) {
		this.userDAO = userDAO;
		this.categoryBean = categoryBean;
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
	
	public CategoryBean getCategoryBean() {
		return categoryBean;
	}

	public void setCategoryBean(CategoryBean categoryBean) {
		this.categoryBean = categoryBean;
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
	
	public List<User> getUsersNotOfTypeAdmin() {
		return userDAO.getUsersNotOfType("A");
	}
	
	public boolean insert(User user) {
		return userDAO.insert(user);
	}
	
	public boolean delete(String username) {
		User user = new User(username);
		return userDAO.delete(user);
	}
	
	public User getUser(String username) {
		return userDAO.getUser(username);
	}
	
	public boolean update(User user) {
		return userDAO.update(user);
	}
	
	public boolean activateAccount(String username, Boolean activate) {
		return userDAO.activateAccount(username, activate);
	}
}
