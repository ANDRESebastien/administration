package jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class testH2 {

	public static void main(String[] args) throws Exception {

		System.out.println(" -> debut test");

		try {
			Class.forName("org.h2.Driver").getConstructor().newInstance();
			Connection connexion = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE;DATABASE_TO_UPPER=false",
					"sa", "");
			//String sql = "SELECT nom FROM ADMINISTRATION WHERE nom = ? ";
			String sql = "SELECT * FROM Administration";

			PreparedStatement instructionSQL = connexion.prepareStatement(sql);
			//instructionSQL.setString(1, "Seb");
			ResultSet result = instructionSQL.executeQuery();

			String nomBDD;

			while (result.next()) {
				nomBDD = result.getString("nom");
				System.out.println("trouvé = " + nomBDD);
			}

			instructionSQL.close();
			connexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}

		System.out.println("Fin BDD Select nom");
	}

}
