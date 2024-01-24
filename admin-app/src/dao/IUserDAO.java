package dao;

import dto.User;

public interface IUserDAO extends IDAO<User>{
	static final String SQL_GET_ALL = "SELECT * FROM user";
	static final String SQL_GET = "SELECT * FROM user WHERE username=? AND password=?";
	static final String SQL_IS_USERNAME_USED = "SELECT * FROM user WHERE username=?";
	static final String SQL_INSERT = "INSERT INTO user (username, password, user_type, name, surname, "
			+ "city, mail, activated) VALUES (?,?,?,?,?,?,?,?)";
	static final String SQL_DELETE = "UPDATE user SET activated=0 user WHERE username=?";
	static final String SQL_ACTIVATE_ACC = "UPDATE user SET activated=1 user WHERE username=?";
	static final String SQL_UPDATE = "UPDATE user SET password=?, user_type=?, name=?, surname=?, "
			+ "city=?, mail=?, activated=? WHERE username=?";
	static final String SQL_UPDATE_USERNAME = "UPDATE user SET username=? WHERE username=?";
	
	boolean usernameExists(String username);
	User login(String username, String password);
	boolean updateUsername(String username, String other);
}
