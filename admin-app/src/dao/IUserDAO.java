package dao;

import java.util.List;

import dto.User;

public interface IUserDAO extends IDAO<User>{
	static final String SQL_GET_USERS_OF_TYPE = "SELECT * FROM user WHERE user_type LIKE ?";
	static final String SQL_GET_USERS_NOT_OF_TYPE = "SELECT * FROM user WHERE user_type NOT LIKE ?";
	static final String SQL_GET = "SELECT * FROM user WHERE username=? AND password=?";
	static final String SQL_IS_USERNAME_USED = "SELECT * FROM user WHERE username=?";
	static final String SQL_INSERT = "INSERT INTO user (username, password, user_type, name, surname, "
			+ "city, mail, activated) VALUES (?,?,?,?,?,?,?,?)";
	static final String SQL_DELETE = "DELETE FROM user WHERE username=?";
	static final String SQL_ACC_ACTIVATION = "UPDATE user SET activated=? user WHERE username=?";
	static final String SQL_UPDATE = "UPDATE user SET password=?, user_type=?, name=?, surname=?, "
			+ "city=?, mail=?, activated=? WHERE username=?";
	static final String SQL_UPDATE_USERNAME = "UPDATE user SET username=? WHERE username=?";
	
	boolean usernameExists(String username);
	User login(String username, String password);
	User getUser(String username);
	boolean updateUsername(String username, String other);
	List<User> getFitnessAppUsers();
	List<User> getUsersWhere(String query, String param);
	List<User> getUsersNotOfType(String type);
}
