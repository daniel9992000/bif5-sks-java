/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "MEASUREMENT")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measurement.findAll", query = "SELECT m FROM Measurement m"),
    @NamedQuery(name = "Measurement.findByMeasureid", query = "SELECT m FROM Measurement m WHERE m.measureid = :measureid"),
    @NamedQuery(name = "Measurement.findByMeasuredate", query = "SELECT m FROM Measurement m WHERE m.measuredate = :measuredate"),
    @NamedQuery(name = "Measurement.findByMeasuretime", query = "SELECT m FROM Measurement m WHERE m.measuretime = :measuretime"),
    @NamedQuery(name = "Measurement.findByMeasurevalue", query = "SELECT m FROM Measurement m WHERE m.measurevalue = :measurevalue")})
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "MEASUREID")
    private Integer measureid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEASUREDATE")
    @Temporal(TemporalType.DATE)
    private Date measuredate;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEASURETIME")
    @Temporal(TemporalType.TIME)
    private Date measuretime;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEASUREVALUE")
    private double measurevalue;
    @JoinColumn(name = "TYPEID", referencedColumnName = "TYPEID")
    @ManyToOne(optional = false)
    private MeasurementType typeid;
    @JoinColumn(name = "INSTALLATIONID", referencedColumnName = "INSTALLATIONID")
    @ManyToOne(optional = false)
    private Installation installationid;

    public Measurement() {
    }

    public Measurement(Integer measureid) {
        this.measureid = measureid;
    }

    public Measurement(Integer measureid, Date measuredate, Date measuretime, double measurevalue) {
        this.measureid = measureid;
        this.measuredate = measuredate;
        this.measuretime = measuretime;
        this.measurevalue = measurevalue;
    }

    public Integer getMeasureid() {
        return measureid;
    }

    public void setMeasureid(Integer measureid) {
        this.measureid = measureid;
    }

    public Date getMeasuredate() {
        return measuredate;
    }

    public void setMeasuredate(Date measuredate) {
        this.measuredate = measuredate;
    }

    public Date getMeasuretime() {
        return measuretime;
    }

    public void setMeasuretime(Date measuretime) {
        this.measuretime = measuretime;
    }

    public double getMeasurevalue() {
        return measurevalue;
    }

    public void setMeasurevalue(double measurevalue) {
        this.measurevalue = measurevalue;
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
        hash += (measureid != null ? measureid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.measureid == null && other.measureid != null) || (this.measureid != null && !this.measureid.equals(other.measureid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.Measurement[ measureid=" + measureid + " ]";
    }
    
}
