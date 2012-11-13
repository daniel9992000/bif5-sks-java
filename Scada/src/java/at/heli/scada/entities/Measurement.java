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
    @NamedQuery(name = "Measurement.findByMeasid", query = "SELECT m FROM Measurement m WHERE m.measid = :measid"),
    @NamedQuery(name = "Measurement.findByMeasuredate", query = "SELECT m FROM Measurement m WHERE m.measuredate = :measuredate"),
    @NamedQuery(name = "Measurement.findByMeasuretime", query = "SELECT m FROM Measurement m WHERE m.measuretime = :measuretime")})
public class Measurement implements Serializable {
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MEASUREVALUE")
    private Double measurevalue;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MEASID")
    private Integer measid;
    @Column(name = "MEASUREDATE")
    @Temporal(TemporalType.DATE)
    private Date measuredate;
    @Column(name = "MEASURETIME")
    @Temporal(TemporalType.TIME)
    private Date measuretime;
    @JoinColumn(name = "TYPEID", referencedColumnName = "TYPEID")
    @ManyToOne
    private MeasurementType typeid;
    @JoinColumn(name = "INSTALLATIONID", referencedColumnName = "INSTALLATIONID")
    @ManyToOne
    private Installation installationid;

    public Measurement() {
    }

    public Measurement(Integer measid) {
        this.measid = measid;
    }

    public Integer getMeasid() {
        return measid;
    }

    public void setMeasid(Integer measid) {
        this.measid = measid;
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

    public Double getMeasurevalue() {
        return measurevalue;
    }

    public void setMeasurevalue(Double measurevalue) {
        this.measurevalue = measurevalue;
    }
    
}
