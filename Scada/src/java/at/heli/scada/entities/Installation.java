/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "INSTALLATION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Installation.findAll", query = "SELECT i FROM Installation i"),
    @NamedQuery(name = "Installation.findByInstallationid", query = "SELECT i FROM Installation i WHERE i.installationid = :installationid"),
    @NamedQuery(name = "Installation.findBySerialno", query = "SELECT i FROM Installation i WHERE i.serialno = :serialno"),
    @NamedQuery(name = "Installation.findByLongitude", query = "SELECT i FROM Installation i WHERE i.longitude = :longitude"),
    @NamedQuery(name = "Installation.findByLatitude", query = "SELECT i FROM Installation i WHERE i.latitude = :latitude"),
    @NamedQuery(name = "Installation.findByDescription", query = "SELECT i FROM Installation i WHERE i.description = :description"),
    @NamedQuery(name = "Installation.findByCustomerId", query = "SELECT i FROM Installation i WHERE i.customerid = :customerid")})
public class Installation implements Serializable {
    @Column(name = "LATITUDE")
    private BigInteger latitude;
    @Column(name = "LONGITUDE")
    private BigInteger longitude;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "INSTALLATIONID")
    private Integer installationid;
    @Size(max = 40)
    @Column(name = "SERIALNO")
    private String serialno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 250)
    @Column(name = "DESCRIPTION")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "installationid")
    private Collection<Measurement> measurementCollection;
    @JoinColumn(name = "CUSTOMERID", referencedColumnName = "PERSONID")
    @ManyToOne(optional = false)
    private Customer customerid;

    public Installation() {
    }

    public Installation(Integer installationid) {
        this.installationid = installationid;
    }

    public Installation(Integer installationid, String description) {
        this.installationid = installationid;
        this.description = description;
    }

    public Integer getInstallationid() {
        return installationid;
    }

    public void setInstallationid(Integer installationid) {
        this.installationid = installationid;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Measurement> getMeasurementCollection() {
        return measurementCollection;
    }

    public void setMeasurementCollection(Collection<Measurement> measurementCollection) {
        this.measurementCollection = measurementCollection;
    }

    public Customer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Customer customerid) {
        this.customerid = customerid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (installationid != null ? installationid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Installation)) {
            return false;
        }
        Installation other = (Installation) object;
        if ((this.installationid == null && other.installationid != null) || (this.installationid != null && !this.installationid.equals(other.installationid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.Installation[ installationid=" + installationid + " ]";
    }

    public BigInteger getLatitude() {
        return latitude;
    }

    public void setLatitude(BigInteger latitude) {
        this.latitude = latitude;
    }

    public BigInteger getLongitude() {
        return longitude;
    }

    public void setLongitude(BigInteger longitude) {
        this.longitude = longitude;
    }
    
}
