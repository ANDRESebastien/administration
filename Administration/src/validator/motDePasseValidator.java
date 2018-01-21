package validator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

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

		System.out.println(" >>> Classe motDePasseValidator : proc�dure validate() = " + nom + " " + motDePasse);

		if (motDePasse.equals(nom)) {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:global",
					new FacesMessage("Le nom utilisateur et le mot de passe doivent �tre diff�rent."));

			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, null));
		} else { // nom diff�rent, le nom est il pr�sent en base ?

			System.out.println(" >>> Classe NomPrenomValidator : proc�dure validate() = nom diff�rent ");
			String nomBDD = "";
			try {
				Class.forName("org.hsqldb.jdbcDriver").getConstructor().newInstance();

				Connection connexion = DriverManager.getConnection(
						"jdbc:hsqldb:file:C:/workspace/administration/Administration/data/baseAdministration", "sa",
						"");

				String sql = "SELECT nom FROM ADMINISTRATION WHERE nom = ? ";

				PreparedStatement instructionSQL = connexion.prepareStatement(sql);
				instructionSQL.setString(1, nom);
				ResultSet result = instructionSQL.executeQuery();

				while (result.next()) {
					nomBDD = result.getString("nom");
					System.out.println("trouv� = " + nomBDD);
				}

				instructionSQL.close();
				connexion.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println("Fin BDD " + nomBDD);

			if (nom.equals(nomBDD)) {
				javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:global",
						new FacesMessage("Le nom utilisateur est d�j� pr�sent en base."));

				throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, null));
			}
		}
	}
}