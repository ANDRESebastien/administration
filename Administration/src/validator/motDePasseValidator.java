package validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.component.UIInput;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import javax.transaction.Transaction;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import bean.Administration;

@FacesValidator
public class motDePasseValidator implements Validator {

	public void validate(FacesContext contexte, UIComponent composant, Object motDePasse) throws ValidatorException {

		UIInput nomUIInput = (UIInput) composant.getAttributes().get("attributNom");
		String nom = (String) nomUIInput.getValue();

		System.out.println(" >>> Classe motDePasseValidator : procédure validate() = " + nom + " " + motDePasse);

		if (motDePasse.equals(nom)) {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("form:global",
					new FacesMessage("Le nom utilisateur et le mot de passe doivent être différent."));

			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, null));
		} else { // nom différent, le nom est il présent en base ?

			System.out.println(" >>> Classe NomPrenomValidator : procédure validate() = nom différent ");

			SessionFactory sessionFactory = null;
			Session session = null;

			try {

				sessionFactory = new Configuration().configure("/ressource/hibernate.cfg.xml").buildSessionFactory();
				session = sessionFactory.openSession();

				Administration administration = session.load(Administration.class, nom);

				System.out.println(administration.getNom() + " " + administration.getMotDePasse());

				if (administration != null) {
					System.out.println(
							" >>> Classe NomPrenomValidator : procédure validate() = Le nom utilisateur est déjà présent en base");
					javax.faces.context.FacesContext.getCurrentInstance().addMessage("form:global",
							new FacesMessage("Le nom utilisateur est déjà présent en base."));

					throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, null, null));
				} else {
					System.out.println(
							" >>> Classe NomPrenomValidator : procédure validate() = nom utilisateur n'est pas présent en base");
				}
			} catch (HibernateException e) {
				e.printStackTrace();
			} finally {
				session.close();
				sessionFactory.close();
			}
		}
	}
}