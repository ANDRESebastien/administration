package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator
public class NomValidator implements Validator {

	public void validate(FacesContext contexte, UIComponent composant, Object nom) throws ValidatorException {
		System.out.println(" >>> Classe NomValidator : procédure validate() = " + nom);
		
		
		/*
		if (nom.toString().length() < 2) {
			throw new ValidatorException(new FacesMessage("Le nom doit contenir au moins deux caractères."));
		}
		*/
	}
}