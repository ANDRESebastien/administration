package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class MotDePasseValidator implements Validator {

	public void validate(FacesContext contexte, UIComponent composant, Object oMotDePasse) throws ValidatorException {

		UIInput nomUIInput = (UIInput) composant.getAttributes().get("attributNom");
		String nom = (String) nomUIInput.getValue();
		
		String motDePasse = (String) oMotDePasse;
		
		if (motDePasse.length() < 2) {
			throw new ValidatorException(new FacesMessage(" Le mot de passe doit contenir au moins deux caract�res."));
		}

		if (motDePasse.equals(nom)) {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:global",
					new FacesMessage(" Le nom utilisateur et le mot de passe doivent �tre diff�rent."));

			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, null));
		}
	}
}