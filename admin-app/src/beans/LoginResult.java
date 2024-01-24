package beans;

public enum LoginResult {	
	USERNAME_NOT_FOUND("Korisničko ime ne postoji!"), 
	WRONG_PASSWORD("Pogrešna lozinka!"), 
	NOT_ACTIVATED("Korisnički nalog nije aktiviran!"),
	NOT_ADMIN("Korisnik nije administrator!"),
	SUCCESS("");
	
	private String description;
	
	LoginResult (String description) {
		this.description = description;
	}
	
	@Override
	public String toString() {
		return description;
	}
}
