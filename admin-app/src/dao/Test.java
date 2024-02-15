package dao;

import dto.Category;

public class Test {

	public static void main(String[] args) {
		CategoryDAO dao = new CategoryDAO();
		for (String c : dao.getCategoryNames()) {
			System.out.println(c);
		}
	}

}
