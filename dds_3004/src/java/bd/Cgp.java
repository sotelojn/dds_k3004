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
import javax.persistence.ManyToOne;
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
@Table(name = "CGP", catalog = "dds", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Cgp.findAll", query = "SELECT c FROM Cgp c"),
    @NamedQuery(name = "Cgp.findByCgpId", query = "SELECT c FROM Cgp c WHERE c.cgpId = :cgpId"),
    @NamedQuery(name = "Cgp.findByDireccion", query = "SELECT c FROM Cgp c WHERE c.direccion = :direccion"),
    @NamedQuery(name = "Cgp.findByZona", query = "SELECT c FROM Cgp c WHERE c.zona = :zona"),
    @NamedQuery(name = "Cgp.findByCantidadConsultas", query = "SELECT c FROM Cgp c WHERE c.cantidadConsultas = :cantidadConsultas")})
public class Cgp implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cgp_id")
    private Integer cgpId;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "zona")
    private String zona;
    @Column(name = "cantidad_consultas")
    private Integer cantidadConsultas;
    @OneToMany(mappedBy = "codCgp")
    private Collection<POIs> pOIsCollection;
    @JoinColumn(name = "servicio", referencedColumnName = "codigo_servicio")
    @ManyToOne
    private Servicios servicio;

    public Cgp() {
    }

    public Cgp(Integer cgpId) {
        this.cgpId = cgpId;
    }

    public Integer getCgpId() {
        return cgpId;
    }

    public void setCgpId(Integer cgpId) {
        this.cgpId = cgpId;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
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

    public Servicios getServicio() {
        return servicio;
    }

    public void setServicio(Servicios servicio) {
        this.servicio = servicio;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cgpId != null ? cgpId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Cgp)) {
            return false;
        }
        Cgp other = (Cgp) object;
        if ((this.cgpId == null && other.cgpId != null) || (this.cgpId != null && !this.cgpId.equals(other.cgpId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.Cgp[ cgpId=" + cgpId + " ]";
    }
    
}
