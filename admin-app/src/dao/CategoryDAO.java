package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

}
