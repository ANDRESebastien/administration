package jpa;

import java.util.regex.Pattern;

public class TestRegex {

	public static void main(String[] args) {
		
		String nom = "t.@tt.to";
		
		if(!Pattern.matches("^[a-zA-Z0-9_.-]{2,}@[a-zA-Z0-9_.-]{2,}\\.[a-zA-Z0-9_.-]{2,}$", nom)) {
			System.out.println(" Le nom n'est pas un email valide.");
		} else {
			System.out.println(" Le nom est un email valide.");
		}
	}
}
