package jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class test {

	public static void main(String[] args) throws Exception {

		System.out.println(" -> debut CreationBase");

		try {
			Class.forName("org.hsqldb.jdbcDriver").getConstructor().newInstance();
			// java -cp hsqldb.jar org.hsqldb.server.Server --database.0
			// file:C:/git/Administration/Administration/data/baseAdministration --dbname.0
			// baseAdministration

			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/baseAdministration",
					"sa", "");
			
			String sql = "SELECT nom FROM ADMINISTRATION WHERE nom = ? ";
			/*
			Statement instructionSQL = connexion.createStatement();

				System.out.println(sql);
				ResultSet result = instructionSQL.executeQuery(sql);
				
			*/

			//String sql = "SELECT nom " + " FROM ADMINISTRATION " + " WHERE nom = ? ";

			PreparedStatement instructionSQL = connexion.prepareStatement(sql);
			instructionSQL.setString(1, "Seb");
			ResultSet result = instructionSQL.executeQuery();


			String nomTable;

			while (result.next()) {
				nomTable = result.getString("nom");
				System.out.println("trouv� = " + nomTable);
			}

			instructionSQL.close();
			connexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}