package beans;

import java.util.List;

import dao.CategoryDAO;
import dao.ICategoryDAO;
import dto.Category;

public class CategoryBean {
	private ICategoryDAO categoryDAO = new CategoryDAO();
	
	public List<Category> getAll() {
		return categoryDAO.getAll();
	}
	
	public List<String> getCategoryNames() {
		return categoryDAO.getCategoryNames();
	}
	
	public List<String> getAttributeNames() {
		return categoryDAO.getAttributeNames();
	}
	
	public boolean insertAttribute(String attribute) {
		return categoryDAO.insertAttribute(attribute);
	}
	
	public int insertCategory(String category) {
		return categoryDAO.insertCategory(category);
	}
	
	public void addCategory(int categoryId, String attribute) {
		categoryDAO.addCategory(categoryId, attribute);
	}
	
	public void deleteCategory(int id) {
		categoryDAO.deleteCategory(id);
	}
	
	public void changeCategory(int id, String categoryName) {
		categoryDAO.changeCategory(id, categoryName);
	}
}
