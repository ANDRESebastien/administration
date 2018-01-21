package dao;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import entity.AdministrationEntity;

@Transactional
@Stateless
public class AdministrationDao {

	@PersistenceContext(unitName = "persistenceAdministration")
	private EntityManager em;

	public void insert(AdministrationEntity administration) throws SQLException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException {

		Class.forName("org.hsqldb.jdbcDriver").getConstructor().newInstance();
		Connection connexion = DriverManager.getConnection("jdbc:hsqldb:file:C:/workspace/administration/Administration/data/baseAdministration",
				"sa", "");
	
		String sql = "INSERT INTO ADMINISTRATION VALUES (?,?)";
		PreparedStatement instructionSQL = connexion.prepareStatement(sql);
		instructionSQL.setString(1, administration.getNom());
		instructionSQL.setString(2, administration.getMotDePasse());
		
		int nombreInsertion  = instructionSQL.executeUpdate();
		instructionSQL.close();
		connexion.close();

		//em.persist(administration);
	}
}