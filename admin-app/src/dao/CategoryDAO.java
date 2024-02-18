package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import dto.Category;

public class CategoryDAO implements ICategoryDAO{
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();

	@Override
	public List<Category> getAll() {
		List<Category> categories = new ArrayList<Category>();
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(SQL_GET_CATEGORIES);
			rs = ps.executeQuery();
			int currId = 0;
			while(rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				String attribute = rs.getString("attribute_name");
				
				if (currId != id) {
					currId = id;
					Category category = new Category(id, name);
					category.getAttributes().add(attribute);
					categories.add(category);
				}
				else {
					Category category = categories.get(categories.size() - 1);
					category.getAttributes().add(attribute);
				}
				
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		
		return categories;
	}

	@Override
	public boolean insert(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean update(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Category category) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<String> getCategoryNames() {
		List<String> catNames = new ArrayList<String>();
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(SQL_CATEGORY_NAMES);
			rs = ps.executeQuery();
			while (rs.next()) {
				catNames.add(rs.getString("name"));
			}
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return catNames;
	}

	@Override
	public List<String> getAttributeNames() {
		List<String> attNames = new ArrayList<String>();
		Connection conn = null;
		ResultSet rs = null;
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = conn.prepareStatement(SQL_ATTRIBUTE_NAMES);
			rs = ps.executeQuery();
			while(rs.next())
				attNames.add(rs.getString("name"));
			ps.close();
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return attNames;
	}

	@Override
	public boolean insertAttribute(String attribute) {
		Connection conn = null;
		Object[] values = {attribute};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_INSERT_ATTRIBUTE, false, values);
			ps.executeUpdate();
			ps.close();
		}
		catch (SQLIntegrityConstraintViolationException e) {
			return false;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return true;
	}

	@Override
	public int insertCategory(String category) {
		int id = 0;
		Connection conn = null;
		ResultSet rs = null;
		Object[] values = {category};
		
		try {
			conn = connectionPool.checkOut();
			PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_INSERT_CATEGORY_NAME, true, values);
			if (ps.executeUpdate() > 0) {
				rs = ps.getGeneratedKeys();
				if (rs.next())
					id = rs.getInt(1);
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			connectionPool.checkIn(conn);
		}
		return id;
	}

	@Override
	public void addCategory(int categoryId, String attribute) {
			Connection conn = null;
			Object[] values = {categoryId, attribute};
			
			try {
				conn = connectionPool.checkOut();
				PreparedStatement ps = DAOUtil.prepareStatement(conn, SQL_ADD_CATEGORY, false, values);
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
}
