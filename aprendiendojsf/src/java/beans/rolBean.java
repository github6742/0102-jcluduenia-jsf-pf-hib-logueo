/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import dao.RolDao;
import dao.RolDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import model.Rol;

/**
 *
 * @author sgrsm
 */
@Named(value = "rolBean")
@RequestScoped
public class rolBean implements Serializable{

    private List<SelectItem> selectOneItemsRol;
    public rolBean() {
    }

    public List<SelectItem> getSelectOneItemsRol() {
        this.selectOneItemsRol = new ArrayList<SelectItem>();
        RolDao rolDao = new RolDaoImpl();
        List<Rol> roles = rolDao.selectItems();
        
        for(Rol rol : roles) {
            SelectItem selecItem = new SelectItem(rol.getId(), rol.getNombre());
            this.selectOneItemsRol.add(selecItem);
        }
        
        return selectOneItemsRol;
    }

    public void setSelectOneItemsRol(List<SelectItem> selectOneItemsRol) {
        this.selectOneItemsRol = selectOneItemsRol;
    }
    
    
}
