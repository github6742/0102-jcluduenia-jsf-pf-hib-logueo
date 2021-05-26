package dao;

import java.util.List;
import model.Usuario;
import org.hibernate.Session;
import util.HibernateUtil;

public class UsuarioDaoImpl implements UsuarioDao {

    @Override
    public Usuario findByUsuario(Usuario usuario) {
        Usuario model = null;
        String metodo = "UsuarioDaoImpl.findByUsuario()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Usuario WHERE usuario = '" + usuario.getUsuario() + "'";
        try {

            sesion.beginTransaction();
            System.out.println(metodo + "-010-antes-model = (Usuario) sesion.createQuery(sql).uniqueResult()");
            model = (Usuario) sesion.createQuery(sql).uniqueResult();
            System.out.println(metodo + "-020-luego-model = (Usuario) sesion.createQuery(sql).uniqueResult()");
            sesion.getTransaction().commit();
            //sesion.beginTransaction().commit();
            //sesion.close();
            System.out.println("findByUsuario-04");
        } catch (Exception e) {
            System.out.println(metodo + "-030-rollback");
            sesion.beginTransaction().rollback();
        } finally {
            System.out.println(metodo + "-050-close");
            sesion.close();
        }
        System.out.println(metodo + "-060-return model");
        return model;
    }

    @Override
    public Usuario login(Usuario usuario) {
        Usuario model = this.findByUsuario(usuario);
        String metodo = "UsuarioDaoImpl.login()";
        if (model != null) {
            if (!usuario.getClave().equals(model.getClave())) {
                model = null;
            }
        }
        return model;
    }

    @Override
    public List<Usuario> findAll() {
        List<Usuario> listado = null;
        String metodo = "UsuarioDaoImpl.findAll()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        String sql = "FROM Usuario u left join fetch u.rol";
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

    @Override
    public boolean create(Usuario usuario) {
        boolean flag = false;
        String metodo = "UsuarioDaoImpl.create()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {

            sesion.beginTransaction();
            System.out.println(metodo + "-010-antes- sesion.update(usuario);");
            sesion.save(usuario);
            System.out.println(metodo + "-020-luego- sesion.update(usuario);");
            sesion.getTransaction().commit();
            //model = (Usuario) sesion.createQuery(sql).uniqueResult();
            //sesion.beginTransaction().commit();
            //sesion.close();
            flag = true;
        } catch (Exception e) {
            System.out.println(metodo + "-030-rollback");
            flag = false;
            sesion.beginTransaction().rollback();
        } finally {
            System.out.println(metodo + "-040-close");
            sesion.close();
        }
        System.out.println(metodo + "-050-return flag;");
        return flag;
    }

    @Override
    public boolean update(Usuario usuario) {
        boolean flag = false;
        String metodo = "UsuarioDaoImpl.update()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {

            sesion.beginTransaction();

            System.out.println(metodo + "-040-usuario.usuario.getUsuario(): " + usuario.getUsuario());
            System.out.println(metodo + "-050-usuario.usuario.getRol(): " + usuario.getRol());
            System.out.println(metodo + "-060-usuario.usuario.getEmail(): " + usuario.getEmail());

            Usuario usuariodb = (Usuario) sesion.load(Usuario.class, usuario.getId());
            
            usuariodb.setUsuario(usuario.getUsuario());
            usuariodb.setRol(usuario.getRol());
            usuariodb.setEmail(usuario.getEmail());

            System.out.println(metodo + "-070-usuario.usuariodb.getUsuario(): " + usuariodb.getUsuario());
            System.out.println(metodo + "-080-usuario.usuariodb.getRol(): " + usuariodb.getRol());
            System.out.println(metodo + "-090-usuario.usuariodb.getEmail(): " + usuariodb.getEmail());

            System.out.println(metodo + "-100-sesion.update(usuariodb)-antes");
            sesion.update(usuariodb); 
            System.out.println(metodo + "-110-sesion.update(usuariodb)-luego");
            sesion.getTransaction().commit();
            //model = (Usuario) sesion.createQuery(sql).uniqueResult();
            //sesion.beginTransaction().commit();
            //sesion.close();
            flag = true;
        } catch (Exception e) {
            System.out.println(metodo + "-090-catch-flag-false-rolbback");
            flag = false;
            sesion.beginTransaction().rollback();
        } finally {
            System.out.println(metodo + "-100-close");
            sesion.close();
        }
        System.out.println(metodo + "-110-return-flag");
        return flag;

    }

    @Override
    public boolean delete(Integer id) {
        boolean flag = false;
        String metodo = "UsuarioDaoImpl.delete()";
        //Session sesion = HibernateUtil.getSessionFactory().getCurrentSession();
        Session sesion = HibernateUtil.getSessionFactory().openSession();
        try {

            sesion.beginTransaction();
            Usuario usuario = (Usuario) sesion.load(Usuario.class, id);
            System.out.println(metodo + "-010-antes-sesion.delete(usuario)");
            System.out.println(metodo + "-010-antes-usuario.emeil:" +usuario.getEmail());
            sesion.delete(usuario);
            System.out.println(metodo + "-020-luego-sesion.delete(usuario)");
            sesion.getTransaction().commit();
            
            
            //sesion.beginTransaction().commit();
            //sesion.close();
            flag = true;
        } catch (Exception e) {
            flag = false;
            System.out.println(metodo + "-030-rollback");
            sesion.beginTransaction().rollback();
        } finally {
            System.out.println(metodo + "-040-close");
            sesion.close();
        }
        System.out.println(metodo + "-050-return flag");
        return flag;

    }

}
