package backingBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import bean.AdministrationBean;
import dao.AdministrationDao;
import entity.AdministrationEntity;

@ManagedBean
@RequestScoped
public class AdministrationBackingBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private AdministrationBean administrationBean;
	private AdministrationEntity administrationEntity;

	@EJB
	private AdministrationDao administrationDao;

	public AdministrationBackingBean() {
		this.administrationBean = new AdministrationBean();
		this.administrationEntity = new AdministrationEntity();
	}

	public AdministrationBean getAdministrationBean() {
		return administrationBean;
	}

	public void setAdministrationBean(AdministrationBean administrationBean) {
		this.administrationBean = administrationBean;
	}

	public String delete(String nom) {
		this.administrationDao.delete(nom);
		return "listenom";
	}

	public String aiguillage() {
		String action = "";
		this.administrationEntity.setNom(this.administrationBean.getNom());
		this.administrationEntity.setMotDePasse(this.administrationBean.getMotDePasse());

		
		AdministrationEntity resultat = this.administrationDao.find(this.administrationBean.getNom());
		System.out.println(" resultat = " + resultat);

		if (resultat != null) {
			javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:nom",
					new FacesMessage(" Le nom utilisateur est déjà présent en base."));
			action = "index";
		} else {
			this.administrationDao.insert(this.administrationEntity);
			action = "listenom";
		}
		return action;
	}

	public String prepareConnexion(String nom) {
		this.administrationBean.setNom(nom);
		return "index";
	}
	
	
	public String connexion() {
		String action = "";
		System.out.println("AdministrationBackingBean:connexion: Bean => nom = " + this.administrationBean.getNom()    + " mdp = " + this.administrationBean.getMotDePasse() );

		AdministrationEntity resultat = this.administrationDao.find(this.administrationBean.getNom());

		if (resultat != null &&  this.administrationBean.getNom().equals(resultat.getNom()) &&  this.administrationBean.getMotDePasse().equals(resultat.getMotDePasse())) {
				action = "acceuil";
			} else {
				javax.faces.context.FacesContext.getCurrentInstance().addMessage("administrationForm:global",
						new FacesMessage(" Le nom utilisateur et/ou mot de passe éronné."));
				action = "index";
			}
		return action;
	}
	
	public void changerMotDePasse() {
		this.administrationEntity.setNom(this.administrationBean.getNom());
		this.administrationEntity.setMotDePasse(this.administrationBean.getMotDePasse());
		
		this.administrationDao.update(this.administrationEntity);
	}

	public List<AdministrationEntity> listeNom() {
		return administrationDao.listeNom();
	}

	public AdministrationEntity getAdministrationEntity() {
		return this.administrationEntity;
	}

	public void setAdministrationEntity(AdministrationEntity administrationEntity) {
		this.administrationEntity = administrationEntity;
	}

	public AdministrationDao getAdministrationDao() {
		return this.administrationDao;
	}

	public void setAdministrationDao(AdministrationDao administrationDao) {
		this.administrationDao = administrationDao;
	}
}