/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import model.Rol;
import model.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;


public class RolDaoImpl implements RolDao{

    @Override
    public List<Rol> selectItems() {
        List<Rol> listado = null;
        String metodo = "RolDaoImpl.findAll()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Rol";
        try {

            sesion.beginTransaction();
            System.out.println(metodo + "-010-antes-listado = sesion.createQuery(sql).list()");
            listado = sesion.createQuery(sql).list();
            System.out.println(metodo + "-020-luego-listado = sesion.createQuery(sql).list()");
            sesion.getTransaction().commit();
            //sesion.beginTransaction().commit();
            //sesion.close();
        } catch (Exception e) {
            System.out.println(metodo + "-030-rollback");
            sesion.beginTransaction().rollback();
        } finally {
            System.out.println(metodo + "-040-close");
            sesion.close();
        }
        System.out.println(metodo + "-050-return listado");
        return listado;
        
    }
}
