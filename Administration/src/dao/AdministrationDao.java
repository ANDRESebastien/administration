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
		em.persist(administration);
	}

	public AdministrationEntity select(String nom) {
		try {
			TypedQuery<AdministrationEntity> query = em
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
		return em.find(AdministrationEntity.class, nom);
	}

	public List<AdministrationEntity> listeNom() {
		return em.createQuery("select a from AdministrationEntity a").getResultList();
	}

	public void delete(String nom) {
		AdministrationEntity administrationEntity = em.find(AdministrationEntity.class, nom);
		em.remove(administrationEntity);
	}
}