package backingBean;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import bean.AdministrationBean;

@ManagedBean
@RequestScoped
public class AdministrationBackingBean implements Serializable  {
	
	private static final long serialVersionUID = 1L;
	
	private AdministrationBean administrationBean;
	
	public AdministrationBackingBean() {
		this.administrationBean = new AdministrationBean();
	}
	
	public AdministrationBean getAdministrationBean() {
		return administrationBean;
	}

	public void setAdministrationBean(AdministrationBean administrationBean) {
		this.administrationBean = administrationBean;
	}

	public String aiguillage() {
		System.out.println(this.administrationBean.getNom());
		return "acceuil";
	}
}
