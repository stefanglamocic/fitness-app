package dao;

import dto.Category;

public interface ICategoryDAO extends IDAO<Category>{
	public static final String SQL_GET_CATEGORIES = "SELECT c.id, c.name, ca.attribute_name "
			+ "FROM category c "
			+ "JOIN category_has_attribute ca "
			+ "ON c.id=ca.category_id "
			+ "ORDER BY c.id";
}
