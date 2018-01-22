package jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AdministrationDao;
import entity.AdministrationEntity;


public class Test {
	
	public static void main(String[] args) throws Exception {

		System.out.println(" -> debut test");

		try {
			Class.forName("org.hsqldb.jdbcDriver").getConstructor().newInstance();
			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:data/baseAdministration;shutdown=true", "sa", "");
			String sql = "SELECT nom FROM ADMINISTRATION WHERE nom = ? ";

			PreparedStatement instructionSQL = connexion.prepareStatement(sql);
			instructionSQL.setString(1, "Seb");
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
		
	
		
		AdministrationEntity administrationEntity = new AdministrationEntity();
		administrationEntity.setNom("Totoo");
		administrationEntity.setMotDePasse("tutu");
		
		AdministrationDao administrationDao = new AdministrationDao();
		administrationDao.insert(administrationEntity);
	}
}
