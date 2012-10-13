/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author Daniel
 */
@Entity
@Table(name = "MEASURETYPE")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Measuretype.findAll", query = "SELECT m FROM Measuretype m"),
    @NamedQuery(name = "Measuretype.findByTypeid", query = "SELECT m FROM Measuretype m WHERE m.typeid = :typeid"),
    @NamedQuery(name = "Measuretype.findByDescription", query = "SELECT m FROM Measuretype m WHERE m.description = :description"),
    @NamedQuery(name = "Measuretype.findByMinvalue", query = "SELECT m FROM Measuretype m WHERE m.minvalue = :minvalue"),
    @NamedQuery(name = "Measuretype.findByMaxvalue", query = "SELECT m FROM Measuretype m WHERE m.maxvalue = :maxvalue"),
    @NamedQuery(name = "Measuretype.findByUnit", query = "SELECT m FROM Measuretype m WHERE m.unit = :unit")})
public class Measuretype implements Serializable {
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
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Column(name = "MINVALUE")
    private BigDecimal minvalue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "MAXVALUE")
    private BigDecimal maxvalue;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "UNIT")
    private String unit;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "typeid")
    private Collection<Measurement> measurementCollection;

    public Measuretype() {
    }

    public Measuretype(Integer typeid) {
        this.typeid = typeid;
    }

    public Measuretype(Integer typeid, String description, BigDecimal minvalue, BigDecimal maxvalue, String unit) {
        this.typeid = typeid;
        this.description = description;
        this.minvalue = minvalue;
        this.maxvalue = maxvalue;
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

    public BigDecimal getMinvalue() {
        return minvalue;
    }

    public void setMinvalue(BigDecimal minvalue) {
        this.minvalue = minvalue;
    }

    public BigDecimal getMaxvalue() {
        return maxvalue;
    }

    public void setMaxvalue(BigDecimal maxvalue) {
        this.maxvalue = maxvalue;
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
        if (!(object instanceof Measuretype)) {
            return false;
        }
        Measuretype other = (Measuretype) object;
        if ((this.typeid == null && other.typeid != null) || (this.typeid != null && !this.typeid.equals(other.typeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.Measuretype[ typeid=" + typeid + " ]";
    }
    
}
