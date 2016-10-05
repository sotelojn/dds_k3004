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
@Table(name = "Servicios", catalog = "dds", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicios.findAll", query = "SELECT s FROM Servicios s"),
    @NamedQuery(name = "Servicios.findByCodigoServicio", query = "SELECT s FROM Servicios s WHERE s.codigoServicio = :codigoServicio"),
    @NamedQuery(name = "Servicios.findByDescripcion", query = "SELECT s FROM Servicios s WHERE s.descripcion = :descripcion")})
public class Servicios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codigo_servicio")
    private Integer codigoServicio;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "servicioBanco")
    private Collection<Bancos> bancosCollection;
    @OneToMany(mappedBy = "servicio")
    private Collection<Cgp> cgpCollection;

    public Servicios() {
    }

    public Servicios(Integer codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public Integer getCodigoServicio() {
        return codigoServicio;
    }

    public void setCodigoServicio(Integer codigoServicio) {
        this.codigoServicio = codigoServicio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<Bancos> getBancosCollection() {
        return bancosCollection;
    }

    public void setBancosCollection(Collection<Bancos> bancosCollection) {
        this.bancosCollection = bancosCollection;
    }

    @XmlTransient
    public Collection<Cgp> getCgpCollection() {
        return cgpCollection;
    }

    public void setCgpCollection(Collection<Cgp> cgpCollection) {
        this.cgpCollection = cgpCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codigoServicio != null ? codigoServicio.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicios)) {
            return false;
        }
        Servicios other = (Servicios) object;
        if ((this.codigoServicio == null && other.codigoServicio != null) || (this.codigoServicio != null && !this.codigoServicio.equals(other.codigoServicio))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.Servicios[ codigoServicio=" + codigoServicio + " ]";
    }
    
}
