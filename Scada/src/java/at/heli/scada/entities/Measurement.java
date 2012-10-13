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
    @NamedQuery(name = "Measurement.findByMesid", query = "SELECT m FROM Measurement m WHERE m.mesid = :mesid"),
    @NamedQuery(name = "Measurement.findByPointoftime", query = "SELECT m FROM Measurement m WHERE m.pointoftime = :pointoftime")})
public class Measurement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "MESID")
    private Integer mesid;
    @Basic(optional = false)
    @NotNull
    @Column(name = "POINTOFTIME")
    private int pointoftime;
    @JoinColumn(name = "TYPEID", referencedColumnName = "TYPEID")
    @ManyToOne(optional = false)
    private Measuretype typeid;
    @JoinColumn(name = "INSTALLATIONID", referencedColumnName = "INSTALLATIONID")
    @ManyToOne(optional = false)
    private Installation installationid;

    public Measurement() {
    }

    public Measurement(Integer mesid) {
        this.mesid = mesid;
    }

    public Measurement(Integer mesid, int pointoftime) {
        this.mesid = mesid;
        this.pointoftime = pointoftime;
    }

    public Integer getMesid() {
        return mesid;
    }

    public void setMesid(Integer mesid) {
        this.mesid = mesid;
    }

    public int getPointoftime() {
        return pointoftime;
    }

    public void setPointoftime(int pointoftime) {
        this.pointoftime = pointoftime;
    }

    public Measuretype getTypeid() {
        return typeid;
    }

    public void setTypeid(Measuretype typeid) {
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
        hash += (mesid != null ? mesid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Measurement)) {
            return false;
        }
        Measurement other = (Measurement) object;
        if ((this.mesid == null && other.mesid != null) || (this.mesid != null && !this.mesid.equals(other.mesid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.Measurement[ mesid=" + mesid + " ]";
    }
    
}
