package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class motDePasseValidator implements Validator {

	public void validate(FacesContext contexte, UIComponent composant, Object motDePasse) throws ValidatorException {
		System.out.println(" >>> Classe motDePasseValidator : procédure validate() = " + motDePasse);

	}
}