package validator;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class motDePasseValidator implements Validator {

	public void validate(FacesContext contexte, UIComponent composant, Object motDePasse) throws ValidatorException {

		UIInput nomUIInput = (UIInput) composant.getAttributes().get("attributNom");
		String nom = (String) nomUIInput.getValue();

		System.out.println(" >>> Classe motDePasseValidator : procédure validate() = " + nom + " " + motDePasse);

		if (motDePasse.equals(nom)) {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:global",
					new FacesMessage("Le nom utilisateur et le mot de passe doivent être différent."));

			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, null));
		} else { // nom différent, le nom est il présent en base ?

			System.out.println(" >>> Classe NomPrenomValidator : procédure validate() = nom différent ");
			try {
				Class.forName("org.hsqldb.jdbcDriver").getConstructor().newInstance();
				// java -cp hsqldb.jar org.hsqldb.server.Server --database.0
				// file:C:/git/Administration/Administration/data/baseAdministration --dbname.0
				// baseAdministration

				
				
				Connection connexion = DriverManager
						.getConnection("jdbc:hsqldb:file:C:/git/Administration/Administration/data/baseAdministration", "sa", "");

				String sql = "SELECT nom FROM ADMINISTRATION WHERE nom = ? ";

				PreparedStatement instructionSQL = connexion.prepareStatement(sql);
				instructionSQL.setString(1, nom);
				ResultSet result = instructionSQL.executeQuery();

				String nomBDD = "";
				while (result.next()) {
					nomBDD = result.getString("nom");
					System.out.println("trouvé = " + nomBDD);
				}
				
				System.out.println("Fin BDD " + nomBDD);

				instructionSQL.close();
				connexion.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}