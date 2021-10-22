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
@Table(name = "dosen")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Dosen.findAll", query = "SELECT d FROM Dosen d")
    , @NamedQuery(name = "Dosen.findByKodeDosen", query = "SELECT d FROM Dosen d WHERE d.kodeDosen = :kodeDosen")
    , @NamedQuery(name = "Dosen.findByNamaDosen", query = "SELECT d FROM Dosen d WHERE d.namaDosen = :namaDosen")})
public class Dosen implements Serializable {

    @Transient
    private PropertyChangeSupport changeSupport = new PropertyChangeSupport(this);

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "kode_dosen")
    private String kodeDosen;
    @Basic(optional = false)
    @Column(name = "nama_dosen")
    private String namaDosen;

    public Dosen() {
    }

    public Dosen(String kodeDosen) {
        this.kodeDosen = kodeDosen;
    }

    public Dosen(String kodeDosen, String namaDosen) {
        this.kodeDosen = kodeDosen;
        this.namaDosen = namaDosen;
    }

    public String getKodeDosen() {
        return kodeDosen;
    }

    public void setKodeDosen(String kodeDosen) {
        String oldKodeDosen = this.kodeDosen;
        this.kodeDosen = kodeDosen;
        changeSupport.firePropertyChange("kodeDosen", oldKodeDosen, kodeDosen);
    }

    public String getNamaDosen() {
        return namaDosen;
    }

    public void setNamaDosen(String namaDosen) {
        String oldNamaDosen = this.namaDosen;
        this.namaDosen = namaDosen;
        changeSupport.firePropertyChange("namaDosen", oldNamaDosen, namaDosen);
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (kodeDosen != null ? kodeDosen.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Dosen)) {
            return false;
        }
        Dosen other = (Dosen) object;
        if ((this.kodeDosen == null && other.kodeDosen != null) || (this.kodeDosen != null && !this.kodeDosen.equals(other.kodeDosen))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
//        return "model.Dosen[ kodeDosen=" + kodeDosen + " ]";
        return kodeDosen + "-" + namaDosen;
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.addPropertyChangeListener(listener);
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        changeSupport.removePropertyChangeListener(listener);
    }
    
}
