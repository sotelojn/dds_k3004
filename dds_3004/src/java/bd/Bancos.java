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
@Table(name = "Bancos", catalog = "dds", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Bancos.findAll", query = "SELECT b FROM Bancos b"),
    @NamedQuery(name = "Bancos.findByBancoId", query = "SELECT b FROM Bancos b WHERE b.bancoId = :bancoId"),
    @NamedQuery(name = "Bancos.findByDireccion", query = "SELECT b FROM Bancos b WHERE b.direccion = :direccion"),
    @NamedQuery(name = "Bancos.findByZona", query = "SELECT b FROM Bancos b WHERE b.zona = :zona"),
    @NamedQuery(name = "Bancos.findByNombre", query = "SELECT b FROM Bancos b WHERE b.nombre = :nombre"),
    @NamedQuery(name = "Bancos.findByCantidadConsultas", query = "SELECT b FROM Bancos b WHERE b.cantidadConsultas = :cantidadConsultas")})
public class Bancos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "banco_id")
    private Integer bancoId;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "zona")
    private String zona;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "cantidad_consultas")
    private Integer cantidadConsultas;
    @JoinColumn(name = "servicio_banco", referencedColumnName = "codigo_servicio")
    @ManyToOne
    private Servicios servicioBanco;
    @OneToMany(mappedBy = "codBanco")
    private Collection<POIs> pOIsCollection;

    public Bancos() {
    }

    public Bancos(Integer bancoId) {
        this.bancoId = bancoId;
    }

    public Integer getBancoId() {
        return bancoId;
    }

    public void setBancoId(Integer bancoId) {
        this.bancoId = bancoId;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCantidadConsultas() {
        return cantidadConsultas;
    }

    public void setCantidadConsultas(Integer cantidadConsultas) {
        this.cantidadConsultas = cantidadConsultas;
    }

    public Servicios getServicioBanco() {
        return servicioBanco;
    }

    public void setServicioBanco(Servicios servicioBanco) {
        this.servicioBanco = servicioBanco;
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
        hash += (bancoId != null ? bancoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Bancos)) {
            return false;
        }
        Bancos other = (Bancos) object;
        if ((this.bancoId == null && other.bancoId != null) || (this.bancoId != null && !this.bancoId.equals(other.bancoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.Bancos[ bancoId=" + bancoId + " ]";
    }
    
}
