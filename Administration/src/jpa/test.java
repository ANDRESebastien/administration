package jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.AdministrationDao;
import entity.AdministrationEntity;


public class test {
	
	public static void main(String[] args) throws Exception {

		System.out.println(" -> debut test");

		try {
			Class.forName("org.hsqldb.jdbcDriver").getConstructor().newInstance();
			// java -cp hsqldb.jar org.hsqldb.server.Server --database.0
			// file:C:/git/Administration/Administration/data/baseAdministration --dbname.0
			// baseAdministration

			Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:C:/workspace/administration/Administration/data/baseAdministration",
					"sa", "");
			
			String sql = "SELECT nom FROM ADMINISTRATION WHERE nom = ? ";

			PreparedStatement instructionSQL = connexion.prepareStatement(sql);
			instructionSQL.setString(1, "Seb");
			ResultSet result = instructionSQL.executeQuery();


			String nomTable;

			while (result.next()) {
				nomTable = result.getString("nom");
				System.out.println("trouvé = " + nomTable);
			}

			instructionSQL.close();
			connexion.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		System.out.println("Fin BDD");
		
		AdministrationEntity administrationEntity = new AdministrationEntity();
		administrationEntity.setNom("Toto");
		administrationEntity.setMotDePasse("tutu");
		
		AdministrationDao administrationDao = new AdministrationDao();
		administrationDao.insert(administrationEntity);
	}
}
