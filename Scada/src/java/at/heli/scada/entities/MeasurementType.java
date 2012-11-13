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
@Table(name = "MEASUREMENT_TYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "MeasurementType.findAll", query = "SELECT m FROM MeasurementType m"),
    @NamedQuery(name = "MeasurementType.findByTypeid", query = "SELECT m FROM MeasurementType m WHERE m.typeid = :typeid"),
    @NamedQuery(name = "MeasurementType.findByDescription", query = "SELECT m FROM MeasurementType m WHERE m.description = :description"),
    @NamedQuery(name = "MeasurementType.findByValuemin", query = "SELECT m FROM MeasurementType m WHERE m.valuemin = :valuemin"),
    @NamedQuery(name = "MeasurementType.findByValuemax", query = "SELECT m FROM MeasurementType m WHERE m.valuemax = :valuemax"),
    @NamedQuery(name = "MeasurementType.findByUnit", query = "SELECT m FROM MeasurementType m WHERE m.unit = :unit")})
public class MeasurementType implements Serializable {
    @Column(name = "VALUEMAX")
    private Double valuemax;
    @Column(name = "VALUEMIN")
    private Double valuemin;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "TYPEID")
    private Integer typeid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "DESCRIPTION")
    private String description;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "UNIT")
    private String unit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeid")
    private Collection<Measurement> measurementCollection;

    public MeasurementType() {
    }

    public MeasurementType(Integer typeid) {
        this.typeid = typeid;
    }

    public MeasurementType(Integer typeid, String description, Double valuemin, Double valuemax, String unit) {
        this.typeid = typeid;
        this.description = description;
        this.valuemin = valuemin;
        this.valuemax = valuemax;
        this.unit = unit;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    @XmlTransient
    public Collection<Measurement> getMeasurementCollection() {
        return measurementCollection;
    }

    public void setMeasurementCollection(Collection<Measurement> measurementCollection) {
        this.measurementCollection = measurementCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (typeid != null ? typeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MeasurementType)) {
            return false;
        }
        MeasurementType other = (MeasurementType) object;
        if ((this.typeid == null && other.typeid != null) || (this.typeid != null && !this.typeid.equals(other.typeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.MeasurementType[ typeid=" + typeid + " ]";
    }

    public Double getValuemax() {
        return valuemax;
    }

    public void setValuemax(Double valuemax) {
        this.valuemax = valuemax;
    }

    public Double getValuemin() {
        return valuemin;
    }

    public void setValuemin(Double valuemin) {
        this.valuemin = valuemin;
    }
    
}
