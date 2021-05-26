package model;
// Generated 16/05/2021 14:42:18 by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Rol generated by hbm2java
 */
@Entity
@Table(name="rol"
    ,catalog="aprendiendojsf"
)
public class Rol  implements java.io.Serializable {


     private Integer id;
     private String nombre;
     private String descripcion;
     private Boolean estado;
     private Set usuarios = new HashSet(0);
     private Set rolmenus = new HashSet(0);

    public Rol() {
        this.id = 0;
    }

	
    public Rol(String nombre) {
        this.nombre = nombre;
    }
    public Rol(String nombre, String descripcion, Boolean estado, Set usuarios, Set rolmenus) {
       this.nombre = nombre;
       this.descripcion = descripcion;
       this.estado = estado;
       this.usuarios = usuarios;
       this.rolmenus = rolmenus;
    }
   
     @Id @GeneratedValue(strategy=IDENTITY)

    
    @Column(name="id", unique=true, nullable=false)
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }

    
    @Column(name="nombre", nullable=false, length=30)
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    
    @Column(name="descripcion", length=100)
    public String getDescripcion() {
        return this.descripcion;
    }
    
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
    @Column(name="estado")
    public Boolean getEstado() {
        return this.estado;
    }
    
    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="rol")
    public Set getUsuarios() {
        return this.usuarios;
    }
    
    public void setUsuarios(Set usuarios) {
        this.usuarios = usuarios;
    }

@OneToMany(fetch=FetchType.LAZY, mappedBy="rol")
    public Set getRolmenus() {
        return this.rolmenus;
    }
    
    public void setRolmenus(Set rolmenus) {
        this.rolmenus = rolmenus;
    }




}


