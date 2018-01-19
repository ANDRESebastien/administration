package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ManagedBean
@RequestScoped
@Entity
@Table(name = "Administration")
public class AdministrationBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private String nom;

	private String motDePasse;
	
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String prenom) {
		this.motDePasse = prenom;
	}
}
