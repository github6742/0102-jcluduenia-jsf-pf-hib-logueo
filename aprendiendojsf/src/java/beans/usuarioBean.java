/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.UsuarioDao;
import dao.UsuarioDaoImpl;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import model.Usuario;

/**
 *
 * @author sgrsm
 */
@Named(value = "usuarioBean")
@RequestScoped
public class usuarioBean implements Serializable {

    private Usuario selectedUsuario;
    private List<Usuario> usuarios;

    public usuarioBean() {
        this.usuarios = new ArrayList<Usuario>();
        this.selectedUsuario = new Usuario();
        
    }

    public Usuario getSelectedUsuario() {
        return selectedUsuario;
    }

    public void setSelectedUsuario(Usuario selectedUsuario) {
        this.selectedUsuario = selectedUsuario;
    }

    public List<Usuario> getUsuarios() {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        this.usuarios = usuarioDao.findAll();
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void btnCreateUsuario(ActionEvent actionEvent) {
        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        this.selectedUsuario.setId(1);
        this.selectedUsuario.setClave(selectedUsuario.getUsuario());
        this.selectedUsuario.setUsuariocreacion("admin");
        Date hoy = new Date();
        String fecha = new SimpleDateFormat("yyyy-MM-dd").format(hoy);
        this.selectedUsuario.setFechacreacion(java.sql.Date.valueOf(fecha));

        if (usuarioDao.create(this.selectedUsuario)) {
            msg = "Se creo el registro correctamente";

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al crear el registro";

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnUpdateUsuario(ActionEvent actionEvent) {

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        if (usuarioDao.update(this.selectedUsuario)) {
            msg = "Se modifico el registro correctamente";

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al modificar el registro";

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }

    }

    public void btnDeleteUsuario(ActionEvent actionEvent) {

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        String msg;
        if (usuarioDao.delete(this.selectedUsuario.getId())) {
            msg = "Se elimino el registro correctamente";

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } else {
            msg = "Error al eliminar el registro";

            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, null);
            FacesContext.getCurrentInstance().addMessage(null, message);
        }


    }

}
