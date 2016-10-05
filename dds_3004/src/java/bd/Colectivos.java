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
@Table(name = "Colectivos", catalog = "dds", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Colectivos.findAll", query = "SELECT c FROM Colectivos c"),
    @NamedQuery(name = "Colectivos.findByColectivoId", query = "SELECT c FROM Colectivos c WHERE c.colectivoId = :colectivoId"),
    @NamedQuery(name = "Colectivos.findByNumeroLinea", query = "SELECT c FROM Colectivos c WHERE c.numeroLinea = :numeroLinea"),
    @NamedQuery(name = "Colectivos.findByCantidadConsultas", query = "SELECT c FROM Colectivos c WHERE c.cantidadConsultas = :cantidadConsultas")})
public class Colectivos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "colectivo_id")
    private Integer colectivoId;
    @Column(name = "numero_linea")
    private Integer numeroLinea;
    @Column(name = "cantidad_consultas")
    private Integer cantidadConsultas;
    @OneToMany(mappedBy = "codColectivo")
    private Collection<POIs> pOIsCollection;

    public Colectivos() {
    }

    public Colectivos(Integer colectivoId) {
        this.colectivoId = colectivoId;
    }

    public Integer getColectivoId() {
        return colectivoId;
    }

    public void setColectivoId(Integer colectivoId) {
        this.colectivoId = colectivoId;
    }

    public Integer getNumeroLinea() {
        return numeroLinea;
    }

    public void setNumeroLinea(Integer numeroLinea) {
        this.numeroLinea = numeroLinea;
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
        hash += (colectivoId != null ? colectivoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Colectivos)) {
            return false;
        }
        Colectivos other = (Colectivos) object;
        if ((this.colectivoId == null && other.colectivoId != null) || (this.colectivoId != null && !this.colectivoId.equals(other.colectivoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.Colectivos[ colectivoId=" + colectivoId + " ]";
    }
    
}
