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
}
