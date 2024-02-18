package dao;

import java.util.List;

import dto.Category;

public interface ICategoryDAO extends IDAO<Category>{
	public static final String SQL_GET_CATEGORIES = "SELECT c.id, c.name, ca.attribute_name "
			+ "FROM category c "
			+ "JOIN category_has_attribute ca "
			+ "ON c.id=ca.category_id "
			+ "ORDER BY c.id";
	public static final String SQL_CATEGORY_NAMES = "SELECT name FROM category";
	public static final String SQL_ATTRIBUTE_NAMES = "SELECT name FROM attribute";
	public static final String SQL_INSERT_CATEGORY_NAME = "INSERT INTO category(name) VALUES (?)";
	public static final String SQL_INSERT_ATTRIBUTE = "INSERT INTO attribute(name) VALUES (?)";
	public static final String SQL_ADD_CATEGORY = "INSERT INTO category_has_attribute(category_id, attribute_name) VALUES (?,?)";
	
	List<String> getCategoryNames();
	List<String> getAttributeNames();
	boolean insertAttribute(String attribute);
	int insertCategory(String category);
	void addCategory(int categoryId, String attribute);
}
