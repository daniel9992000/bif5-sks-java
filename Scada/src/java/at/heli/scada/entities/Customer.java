/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.entities;

import java.io.Serializable;
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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "CUSTOMER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customer.findAll", query = "SELECT c FROM Customer c"),
    @NamedQuery(name = "Customer.findByEngineerId", query = "SELECT c FROM Customer c WHERE c.engineerid = :engineerid")})
public class Customer extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @JoinColumn(name = "PERSONID", referencedColumnName = "PERSONID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;
    @JoinColumn(name = "ENGINEERID", referencedColumnName = "PERSONID")
    @ManyToOne(optional = false)
    private Engineer engineerid;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerid")
    private Collection<Installation> installationCollection;

    public Customer() {
        super();
    }
    
    public Customer(Integer personid)
    {
        super(personid);
    }


    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Engineer getEngineerid() {
        return engineerid;
    }

    public void setEngineerid(Engineer engineerid) {
        this.engineerid = engineerid;
    }

    @XmlTransient
    public Collection<Installation> getInstallationCollection() {
        return installationCollection;
    }

    public void setInstallationCollection(Collection<Installation> installationCollection) {
        this.installationCollection = installationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getPersonid() != null ? this.getPersonid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.getPersonid() == null && other.getPersonid() != null) || (this.getPersonid() != null && !this.getPersonid().equals(other.getPersonid()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.Customer[ personid=" + getPersonid() + " ]";
    }
    
}
