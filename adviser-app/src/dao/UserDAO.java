package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.User;

public class UserDAO {
	private static final String SQL_LOGIN = "SELECT * FROM user WHERE username=? AND password=?";
	private static final String SQL_USER_EXISTS = "SELECT * FROM user WHERE username=?";
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	public boolean userExists(String username) {
		boolean result = false;
		Connection conn = null;
		ResultSet rs = null;
		Object[] values = {username};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_USER_EXISTS, false, values);
			rs = ps.executeQuery();
			if (rs.next())
				result = true;
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return result;
	}
	
	public User login(String username, String password) {
		User user = null;
		Connection conn = null;
		ResultSet rs = null;
		Object[] values = {username, password};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_LOGIN, false, values);
			rs = ps.executeQuery();
			if (rs.next()) {
				user = getUser(rs);
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return user;
	}
	
	private User getUser(ResultSet rs) throws SQLException{
		return new User(rs.getString("username"), rs.getString("password"), rs.getString("user_type"), rs.getString("name"), 
				rs.getString("surname"), rs.getString("mail"), rs.getBoolean("activated	"));
	}
}
