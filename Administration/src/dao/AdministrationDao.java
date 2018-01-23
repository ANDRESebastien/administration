package dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import entity.AdministrationEntity;

@Stateless
public class AdministrationDao {

	@PersistenceContext(unitName = "persistence-unit-h2")
	private EntityManager em;

	public void insert(AdministrationEntity administration) {
		this.em.persist(administration);
	}

	public AdministrationEntity select(String nom) {
		try {
			TypedQuery<AdministrationEntity> query = this.em
					.createQuery("SELECT a FROM AdministrationEntity a WHERE a.nom = ?1", AdministrationEntity.class);

			AdministrationEntity administrationEntity = query.setParameter(1, nom).getSingleResult();
			if (administrationEntity != null) {
				System.out.println("AdministrationDao:select: On a trouvé le nom " + administrationEntity.getNom());
			}
			return administrationEntity;
		} catch (Exception e) {
			return null;
		}
	}

	public AdministrationEntity find(String nom) {
		return this.em.find(AdministrationEntity.class, nom);
	}

	public List<AdministrationEntity> listeNom() {
		return this.em.createQuery("select a from AdministrationEntity a").getResultList();
	}

	public AdministrationEntity update(AdministrationEntity administrationEntity) {
		if (this.find(administrationEntity.getNom()) != null) {
			return this.em.merge(administrationEntity);
		} else {
			return null;
		}
	}

	public String delete(String nom) {
		AdministrationEntity administrationEntity = this.find(nom);
		if (administrationEntity != null) {
			this.em.remove(administrationEntity);
			return "Ok";
		} else {
			return "nom non trouvé";
		}
	}
}