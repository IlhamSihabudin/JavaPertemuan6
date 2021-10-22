/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Ilham Sihabudin
 */
@Entity
@Table(name = "fakultas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fakultas.findAll", query = "SELECT f FROM Fakultas f")
    , @NamedQuery(name = "Fakultas.findByKodeFakultas", query = "SELECT f FROM Fakultas f WHERE f.kodeFakultas = :kodeFakultas")
    , @NamedQuery(name = "Fakultas.findByNamaFakultas", query = "SELECT f FROM Fakultas f WHERE f.namaFakultas = :namaFakultas")})
public class Fakultas implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kode_fakultas")
    private String kodeFakultas;
    @Basic(optional = false)
    @Column(name = "nama_fakultas")
    private String namaFakultas;

    public Fakultas() {
    }

    public Fakultas(String kodeFakultas) {
        this.kodeFakultas = kodeFakultas;
    }

    public Fakultas(String kodeFakultas, String namaFakultas) {
        this.kodeFakultas = kodeFakultas;
        this.namaFakultas = namaFakultas;
    }

    public String getKodeFakultas() {
        return kodeFakultas;
    }

    public void setKodeFakultas(String kodeFakultas) {
        String oldKodeFakultas = this.kodeFakultas;
        this.kodeFakultas = kodeFakultas;
        changeSupport.firePropertyChange("kodeFakultas", oldKodeFakultas, kodeFakultas);
    }

    public String getNamaFakultas() {
        return namaFakultas;
    }

    public void setNamaFakultas(String namaFakultas) {
        String oldNamaFakultas = this.namaFakultas;
        this.namaFakultas = namaFakultas;
        changeSupport.firePropertyChange("namaFakultas", oldNamaFakultas, namaFakultas);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeFakultas != null ? kodeFakultas.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fakultas)) {
            return false;
        }
        Fakultas other = (Fakultas) object;
        if ((this.kodeFakultas == null && other.kodeFakultas != null) || (this.kodeFakultas != null && !this.kodeFakultas.equals(other.kodeFakultas))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "model.Fakultas[ kodeFakultas=" + kodeFakultas + " ]";
        return kodeFakultas + "-" + namaFakultas;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
