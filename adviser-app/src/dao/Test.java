package dao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Test {

	public static void main(String[] args) {
		MessageDAO dao = new MessageDAO();
		String time = dao.getAll("marko").get(0).getTimeSent();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
		LocalDateTime ldt = LocalDateTime.parse(time, formatter);
		System.out.println(ldt);
		System.out.println(ldt.format(formatter2));
	}

}
