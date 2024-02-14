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
}
