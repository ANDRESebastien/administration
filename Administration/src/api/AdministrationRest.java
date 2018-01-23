package api;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import dao.AdministrationDao;
import entity.AdministrationEntity;

@Path("/service")
public class AdministrationRest {

	@EJB
	private AdministrationDao administrationDao;

	@GET
	@Path("/recherche")
	@Produces("application/json")
	public AdministrationEntity recherche(@QueryParam("nom") String nom) {
		return this.administrationDao.find(nom);
	}

	@POST
	@Path("/insert")
	@Produces("text/html")
	public String insert(@QueryParam("nom") String nom, @QueryParam("mdp") String motDePasse) {
		AdministrationEntity administrationEntity = new AdministrationEntity();
		administrationEntity.setNom(nom);
		administrationEntity.setMotDePasse(motDePasse);
		this.administrationDao.insert(administrationEntity);
		return "Ok";
	}
	
	@POST
	@Path("/delete")
	@Produces("text/html")
	public String delete(@QueryParam("nom") String nom, @QueryParam("mdp") String motDePasse) {
		AdministrationEntity administrationEntity = new AdministrationEntity();
		administrationEntity.setNom(nom);
		administrationEntity.setMotDePasse(motDePasse);
		this.administrationDao.insert(administrationEntity);
		return "Ok";
	}

	@GET
	@Path("/listenom")
	@Produces("application/json")
	public List<AdministrationEntity> listeNom() {
		return this.administrationDao.listeNom();
	}
	
	@POST
	@Path("/update")
	@Produces("text/html")
	public String update(@QueryParam("nom") String nom, @QueryParam("mdp") String motDePasse) {
		AdministrationEntity administrationEntity = new AdministrationEntity();
		administrationEntity.setNom(nom);
		administrationEntity.setMotDePasse(motDePasse);
		AdministrationEntity update = this.administrationDao.update(administrationEntity);
		
		if (update != null) {
			return "Ok";
		} else {
			return "Ko";
		}
	}
	
	@POST
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	public String update(AdministrationEntity administrationEntity) {
		
		System.out.println("AdministrationRest:update:@ConsumesJson: nom=" + administrationEntity.getNom() + " mdp=" + administrationEntity.getMotDePasse());
		
		AdministrationEntity update = this.administrationDao.update(administrationEntity);
		
		if (update != null) {
			System.out.println("update nom ="+ update.getNom() + " mdp=" + update.getMotDePasse());
			return "Ok";
		} else {
			return "Ko";
		}
	}
	
	@POST
	@Path("/insert")
	@Consumes(MediaType.APPLICATION_JSON)
	public String insert(AdministrationEntity administrationEntity) {
		this.administrationDao.insert(administrationEntity);
		return "Ok";
	}
}