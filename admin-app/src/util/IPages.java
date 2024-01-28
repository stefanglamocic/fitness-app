package util;

public interface IPages {
	static final String LOGIN = "/WEB-INF/login.jsp";
	static final String MAIN = "/WEB-INF/main.jsp";
	static final String FITNESS_PROGRAMS = MAIN;
	static final String USERS = "/WEB-INF/users.jsp";
	static final String STATS = "/WEB-INF/stats.jsp";
	
	static String pageMapping(String page) {
		switch (page) {
		case "second": 
			return "Korisnici";
		case "third":
			return "Statistika";
			default:
				return "Kategorije";
		}
	}
}
