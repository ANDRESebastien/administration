package validator;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import dao.AdministrationDao;
import entity.AdministrationEntity;

@FacesValidator
public class motDePasseValidator implements Validator {
	
    @EJB
    private AdministrationDao administrationDao;

	public void validate(FacesContext contexte, UIComponent composant, Object oMotDePasse) throws ValidatorException {

		UIInput nomUIInput = (UIInput) composant.getAttributes().get("attributNom");
		String nom = (String) nomUIInput.getValue();
		
		String motDePasse = (String) oMotDePasse;
		
		if (motDePasse.length() < 2) {
			throw new ValidatorException(new FacesMessage(" Le mot de passe doit contenir au moins deux caractères."));
		}

		if (motDePasse.equals(nom)) {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:global",
					new FacesMessage("Le nom utilisateur et le mot de passe doivent être différent."));

			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, null));
		}
	}
}