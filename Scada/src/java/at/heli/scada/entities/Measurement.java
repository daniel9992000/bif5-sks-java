/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.entities;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Daniel
 */
@Entity
@Table(name = "MEASUREMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measurement.findAll", query = "SELECT m FROM Measurement m"),
    @NamedQuery(name = "Measurement.findByMeasid", query = "SELECT m FROM Measurement m WHERE m.measid = :measid"),
    @NamedQuery(name = "Measurement.findByTimestamp", query = "SELECT m FROM Measurement m WHERE m.timestamp = :timestamp")})
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEASID")
    private Integer measid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "TIMESTAMP")
    private int timestamp;
    @JoinColumn(name = "TYPEID", referencedColumnName = "TYPEID")
    @ManyToOne(optional = false)
    private MeasurementType typeid;
    @JoinColumn(name = "INSTALLATIONID", referencedColumnName = "INSTALLATIONID")
    @ManyToOne(optional = false)
    private Installation installationid;

    public Measurement() {
    }

    public Measurement(Integer measid) {
        this.measid = measid;
    }

    public Measurement(Integer measid, int timestamp) {
        this.measid = measid;
        this.timestamp = timestamp;
    }

    public Integer getMeasid() {
        return measid;
    }

    public void setMeasid(Integer measid) {
        this.measid = measid;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public MeasurementType getTypeid() {
        return typeid;
    }

    public void setTypeid(MeasurementType typeid) {
        this.typeid = typeid;
    }

    public Installation getInstallationid() {
        return installationid;
    }

    public void setInstallationid(Installation installationid) {
        this.installationid = installationid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (measid != null ? measid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.measid == null && other.measid != null) || (this.measid != null && !this.measid.equals(other.measid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.Measurement[ measid=" + measid + " ]";
    }
    
}
