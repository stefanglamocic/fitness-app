package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.Message;

public class MessageDAO {
	private static final String SQL_GET_MESSAGES = "SELECT * FROM message WHERE receiver=? ORDER BY time_sent";
	private static final String SQL_OPEN_MSG = "UPDATE message SET opened='1' WHERE sender=? AND receiver=? AND time_sent=?";
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	
	public void openMessage(String sender, String receiver, String timeSent) {
		Connection conn = null;
		Object[] values = {sender, receiver, timeSent};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_OPEN_MSG, false, values);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
	}
	
	public List<Message> getAll(String receiver) {
		List<Message> messages = new ArrayList<Message>();
		Connection conn = null;
		ResultSet rs = null;
		Object[] values = {receiver};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_GET_MESSAGES, false, values);
			rs = ps.executeQuery();
			while (rs.next()) {
				messages.add(getMessage(rs));
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		
		return messages;
	}
	
	private Message getMessage(ResultSet rs) throws SQLException {
		return new Message(rs.getString("content"), rs.getString("time_sent"), rs.getBoolean("opened"), 
				rs.getString("sender"), rs.getString("receiver"));
	}
}
