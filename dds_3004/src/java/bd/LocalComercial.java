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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebaaz
 */
@Entity
@Table(name = "LocalComercial", catalog = "dds", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "LocalComercial.findAll", query = "SELECT l FROM LocalComercial l"),
    @NamedQuery(name = "LocalComercial.findByLocalId", query = "SELECT l FROM LocalComercial l WHERE l.localId = :localId"),
    @NamedQuery(name = "LocalComercial.findByDireccion", query = "SELECT l FROM LocalComercial l WHERE l.direccion = :direccion"),
    @NamedQuery(name = "LocalComercial.findByNombre", query = "SELECT l FROM LocalComercial l WHERE l.nombre = :nombre"),
    @NamedQuery(name = "LocalComercial.findByRubro", query = "SELECT l FROM LocalComercial l WHERE l.rubro = :rubro"),
    @NamedQuery(name = "LocalComercial.findByCantidadConsultas", query = "SELECT l FROM LocalComercial l WHERE l.cantidadConsultas = :cantidadConsultas")})
public class LocalComercial implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "local_id")
    private Integer localId;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "rubro")
    private String rubro;
    @Column(name = "cantidad_consultas")
    private Integer cantidadConsultas;
    @OneToMany(mappedBy = "codLocal")
    private Collection<POIs> pOIsCollection;

    public LocalComercial() {
    }

    public LocalComercial(Integer localId) {
        this.localId = localId;
    }

    public Integer getLocalId() {
        return localId;
    }

    public void setLocalId(Integer localId) {
        this.localId = localId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRubro() {
        return rubro;
    }

    public void setRubro(String rubro) {
        this.rubro = rubro;
    }

    public Integer getCantidadConsultas() {
        return cantidadConsultas;
    }

    public void setCantidadConsultas(Integer cantidadConsultas) {
        this.cantidadConsultas = cantidadConsultas;
    }

    @XmlTransient
    public Collection<POIs> getPOIsCollection() {
        return pOIsCollection;
    }

    public void setPOIsCollection(Collection<POIs> pOIsCollection) {
        this.pOIsCollection = pOIsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (localId != null ? localId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof LocalComercial)) {
            return false;
        }
        LocalComercial other = (LocalComercial) object;
        if ((this.localId == null && other.localId != null) || (this.localId != null && !this.localId.equals(other.localId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.LocalComercial[ localId=" + localId + " ]";
    }
    
}
