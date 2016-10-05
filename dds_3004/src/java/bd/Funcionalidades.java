/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebaaz
 */
@Entity
@Table(name = "Funcionalidades", catalog = "dds", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Funcionalidades.findAll", query = "SELECT f FROM Funcionalidades f"),
    @NamedQuery(name = "Funcionalidades.findByFuncionalidadId", query = "SELECT f FROM Funcionalidades f WHERE f.funcionalidadId = :funcionalidadId"),
    @NamedQuery(name = "Funcionalidades.findByNombre", query = "SELECT f FROM Funcionalidades f WHERE f.nombre = :nombre")})
public class Funcionalidades implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "funcionalidad_id")
    private Integer funcionalidadId;
    @Column(name = "nombre")
    private String nombre;
    @JoinTable(name = "Rol_Funcionalidades", joinColumns = {
        @JoinColumn(name = "funcionalidad_id", referencedColumnName = "funcionalidad_id")}, inverseJoinColumns = {
        @JoinColumn(name = "rol_id", referencedColumnName = "rol_id")})
    @ManyToMany
    private Collection<Rol> rolCollection;

    public Funcionalidades() {
    }

    public Funcionalidades(Integer funcionalidadId) {
        this.funcionalidadId = funcionalidadId;
    }

    public Integer getFuncionalidadId() {
        return funcionalidadId;
    }

    public void setFuncionalidadId(Integer funcionalidadId) {
        this.funcionalidadId = funcionalidadId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public Collection<Rol> getRolCollection() {
        return rolCollection;
    }

    public void setRolCollection(Collection<Rol> rolCollection) {
        this.rolCollection = rolCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (funcionalidadId != null ? funcionalidadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Funcionalidades)) {
            return false;
        }
        Funcionalidades other = (Funcionalidades) object;
        if ((this.funcionalidadId == null && other.funcionalidadId != null) || (this.funcionalidadId != null && !this.funcionalidadId.equals(other.funcionalidadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.Funcionalidades[ funcionalidadId=" + funcionalidadId + " ]";
    }
    
}
