package backingBean;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import bean.AdministrationBean;
import dao.AdministrationDao;
import entity.AdministrationEntity;

@ManagedBean
@RequestScoped
public class AdministrationBackingBean implements Serializable  {
	
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

	public String aiguillage() throws InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, SQLException {
		this.administrationEntity.setNom(this.administrationBean.getNom());
		this.administrationEntity.setMotDePasse(this.administrationBean.getMotDePasse());
		administrationDao.insert(this.administrationEntity);
		//System.out.println(this.administrationBean.getNom());
		return "acceuil";
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