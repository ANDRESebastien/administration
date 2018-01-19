package bean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class Administration implements Serializable {

	private static final long serialVersionUID = 1L;

	private String nom;

	private String motDePasse;
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return motDePasse;
	}

	public void setPrenom(String prenom) {
		this.motDePasse = prenom;
	}
}
