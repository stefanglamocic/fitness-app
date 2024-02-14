package dao;

import dto.Category;

public class Test {

	public static void main(String[] args) {
		CategoryDAO dao = new CategoryDAO();
		for (Category c : dao.getAll()) {
			System.out.println(c.getId() + ": " + c.getName() + " => " + c.getAttributes());
		}
	}

}
