package jpa;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class CreationBase {

	public final static String[] QUERIES = {
			"drop table Administration if exists",
			"create table Administration (id bigint not null, nom varchar(255), motDePasse varchar(255), primary key (id))",
	};

	public static void main(String[] args) throws Exception {

		Class.forName("org.hsqldb.jdbcDriver").getConstructor().newInstance();
		//  java -cp hsqldb.jar org.hsqldb.server.Server --database.0 file:/data/basejpa --dbname.0 basejpa
		
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost:9001/baseAdministration", "sa",  "");
		
		Statement instructionSQL = connexion.createStatement();

		for (String query : QUERIES) {
			System.out.println(query);
			instructionSQL.executeUpdate(query);
		}

		instructionSQL.close();
		connexion.close();

	}

}
