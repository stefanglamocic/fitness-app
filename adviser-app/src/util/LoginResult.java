package util;

public enum LoginResult {
	SUCCESS(""), 
	NO_USER("Korisničko ime ne postoji!"), 
	WRONG_PASSWORD("Pogrešna lozinka!"), 
	WRONG_USERTYPE("Korisnik nije savjetnik!"),
	NOT_ACTIVATED("Korisnički nalog nije aktiviran!");
	
	private String message;
	
	LoginResult(String message) {
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}
}
