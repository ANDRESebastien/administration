package jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreationBase {


	public final static String[] QUERIES = {
			"drop table Administration if exists",
			"drop sequence hibernate_sequence if exists",
			"create sequence hibernate_sequence start with 1 increment by 1",
			"create table Administration (nom varchar(255) not null, motDePasse varchar(255), primary key (nom))",
			"INSERT INTO Administration VALUES('Seb','toto')"
	};


	public static void main(String[] args) throws Exception {
		 
		System.out.println(" -> debut CreationBase");

		Class.forName("org.hsqldb.jdbcDriver").getDeclaredConstructor().newInstance();
		//  java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:C:/git/Administration/Administration/data/baseAdministration --dbname.0 baseAdministration
		
		//Connection connexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/baseAdministration", "sa",  "");
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:data/baseAdministration", "sa",  "");
		
		Statement instructionSQL = connexion.createStatement();

		for (String query : QUERIES) {
			System.out.println(query);
			instructionSQL.executeUpdate(query);
		}

		instructionSQL.close();
		connexion.close();

	}
}