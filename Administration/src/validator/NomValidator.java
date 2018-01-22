package validator;

import java.util.regex.Pattern;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class NomValidator implements Validator {

	public void validate(FacesContext contexte, UIComponent composant, Object oNom) throws ValidatorException {
		String nom = (String) oNom;

		if (nom.length() < 2) {
			throw new ValidatorException(new FacesMessage(" Le nom utilisateur doit contenir au moins deux caractères."));
		} else {
			if(!Pattern.matches("^[a-zA-Z0-9_.-]{2,}@[a-zA-Z0-9_.-]{2,}\\.[a-zA-Z0-9_.-]{2,}$", nom)) {
				throw new ValidatorException(new FacesMessage(" Le nom n'est pas un email valide."));
			}
		}
	}
}