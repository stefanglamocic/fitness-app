package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.User;

public class UserDAO implements IUserDAO{
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	@Override
	public List<User> getAll() {
		return getUsersWhere(SQL_GET_USERS_OF_TYPE, "_");
	}
	
	@Override
	public List<User> getFitnessAppUsers() {
		return getUsersWhere(SQL_GET_USERS_OF_TYPE, "F");
	}
	
	@Override
	public List<User> getUsersNotOfType(String type) {
		return getUsersWhere(SQL_GET_USERS_NOT_OF_TYPE, type);
	}
	
	@Override
	public List<User> getUsersWhere(String query, String param) {
		List<User> users = new ArrayList<User>();
		Connection conn =  null;
		ResultSet rs = null;
		Object[] values = {param};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, query, false, values);
			rs = ps.executeQuery();
			while(rs.next()) {
				users.add(getUser(rs));
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return users;
	}

	@Override
	public boolean insert(User user) {
		if (usernameExists(user.getUsername()))
			return false;
		boolean result = false;
		Connection conn = null;
		Object[] values = {user.getUsername(), user.getPassword(), user.getTypeAbbr(), 
				user.getName(), user.getSurname(), user.getCity(), user.getMail(), user.getActivated()};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_INSERT, false, values);
			ps.executeUpdate();
			if(ps.getUpdateCount() > 0)
				result = true;
			ps.close();
		}
		catch (SQLException e) {
			//log exception
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return result;
	}

	@Override
	public boolean update(User user) {
		if (!usernameExists(user.getUsername()))
			return false;
		boolean result = false;
		Connection conn = null;
		Object[] values = {user.getPassword(), user.getUserType(), 
				user.getName(), user.getSurname(), user.getCity(), user.getMail(), user.getActivated(), user.getUsername()};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_UPDATE, false, values);
			ps.executeUpdate();
			if (ps.getUpdateCount() > 0)
				result = true;
			ps.close();
		}
		catch (SQLException e) {
			//log exception
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return result;
	}

	@Override
	public boolean updateUsername(String username, String other) {
		if (!usernameExists(username))
			return false;
		boolean result = false;
		Connection conn = null;
		Object[] values = {other, username};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_UPDATE_USERNAME, false, values);
			ps.executeUpdate();
			if (ps.getUpdateCount() > 0)
				result = true;
			ps.close();
		}
		catch (SQLException e) {
			//log exception
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		
		return result;
	}

	@Override
	public boolean delete(User user) {
		if (!usernameExists(user.getUsername()))
			return false;
		
		boolean result = false;
		Connection conn = null;
		Object[] values = {user.getUsername()};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_DELETE, false, values);
			ps.executeUpdate();
			if (ps.getUpdateCount() > 0)
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

	@Override
	public boolean usernameExists(String username) {
		Connection conn = null;
		Object[] values = {username};
		ResultSet rs = null;
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_IS_USERNAME_USED, false, values);
			rs = ps.executeQuery();
			if (rs.next())
				return true;
			
			ps.close();
		}
		catch(SQLException e) {
			//log exception...
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return false;
	}

	@Override
	public User login(String username, String password) {
		User user = null;
		Connection conn = null;
		ResultSet rs = null;
		Object[] values = {username, password};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_GET, false, values);
			rs = ps.executeQuery();
			if(rs.next()) {
				user = getUser(rs);
			}
			ps.close();
		}
		catch (SQLException e) {
			//log exception
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return user;
	}
	
	@Override
	public User getUser(String username) {
		User user = null;
		Connection conn = null;
		ResultSet rs = null;
		Object[] values = {username};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_IS_USERNAME_USED, false, values);
			rs = ps.executeQuery();
			if (rs.next())
				user = getUser(rs);
			ps.close();
		}
		catch (SQLException e) {
			// TODO: handle exception
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return user;
	}
	
	private User getUser(ResultSet rs) throws SQLException{
		return new User(rs.getString("username"), rs.getString("password"), rs.getString("user_type"), 
				rs.getString("name"), rs.getString("surname"), rs.getString("city"), rs.getString("mail"), 
				rs.getBoolean("activated"));
	}

}
