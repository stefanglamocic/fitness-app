package dao;

import dto.Category;

public class Test {

	public static void main(String[] args) {
		CategoryDAO dao = new CategoryDAO();
		int id = dao.insertCategory("Test");
		System.out.println(id);
	}

}
