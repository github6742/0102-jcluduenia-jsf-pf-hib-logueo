package beans;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@Named(value = "menuBean")
@RequestScoped
public class menuBean {

    public menuBean() {
    }
    
    public void save() {
        addMessage("Save", "Data saved");
    }

    public void update() {
        addMessage("Update", "Data updated");
    }

    public void delete() {
        
        addMessage("Delete", "Data delete");
        /*FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, "Delete", "Data deleted");
        FacesContext.getCurrentInstance().addMessage(null, message);*/
    }

    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
