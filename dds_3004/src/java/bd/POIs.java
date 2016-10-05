/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author sebaaz
 */
@Entity
@Table(name = "POIs", catalog = "dds", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "POIs.findAll", query = "SELECT p FROM POIs p"),
    @NamedQuery(name = "POIs.findByPoiId", query = "SELECT p FROM POIs p WHERE p.poiId = :poiId"),
    @NamedQuery(name = "POIs.findByTipo", query = "SELECT p FROM POIs p WHERE p.tipo = :tipo")})
public class POIs implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "poi_id")
    private Integer poiId;
    @Column(name = "tipo")
    private String tipo;
    @JoinColumn(name = "cod_banco", referencedColumnName = "banco_id")
    @ManyToOne
    private Bancos codBanco;
    @JoinColumn(name = "cod_cgp", referencedColumnName = "cgp_id")
    @ManyToOne
    private Cgp codCgp;
    @JoinColumn(name = "cod_colectivo", referencedColumnName = "colectivo_id")
    @ManyToOne
    private Colectivos codColectivo;
    @JoinColumn(name = "cod_local", referencedColumnName = "local_id")
    @ManyToOne
    private LocalComercial codLocal;

    public POIs() {
    }

    public POIs(Integer poiId) {
        this.poiId = poiId;
    }

    public Integer getPoiId() {
        return poiId;
    }

    public void setPoiId(Integer poiId) {
        this.poiId = poiId;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Bancos getCodBanco() {
        return codBanco;
    }

    public void setCodBanco(Bancos codBanco) {
        this.codBanco = codBanco;
    }

    public Cgp getCodCgp() {
        return codCgp;
    }

    public void setCodCgp(Cgp codCgp) {
        this.codCgp = codCgp;
    }

    public Colectivos getCodColectivo() {
        return codColectivo;
    }

    public void setCodColectivo(Colectivos codColectivo) {
        this.codColectivo = codColectivo;
    }

    public LocalComercial getCodLocal() {
        return codLocal;
    }

    public void setCodLocal(LocalComercial codLocal) {
        this.codLocal = codLocal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poiId != null ? poiId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof POIs)) {
            return false;
        }
        POIs other = (POIs) object;
        if ((this.poiId == null && other.poiId != null) || (this.poiId != null && !this.poiId.equals(other.poiId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "bd.POIs[ poiId=" + poiId + " ]";
    }
    
}
