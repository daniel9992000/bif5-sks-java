/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package at.heli.scada.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author daniel
 */
@Entity
@Table(name = "ENGINEER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Engineer.findAll", query = "SELECT e FROM Engineer e")})
public class Engineer extends Person implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engineerid")
    private Collection<Customer> customerCollection;
    @JoinColumn(name = "PERSONID", referencedColumnName = "PERSONID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Person person;

    public Engineer() {
        super();
    }

    public Engineer(Integer personid) {
        super(personid);
    }
    
    public Engineer(Integer personid, String firstname, String lastname, String username, String password, String email) {
        super(personid, firstname, lastname, username, password, email);
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (this.getPersonid()!= null ? this.getPersonid().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Engineer)) {
            return false;
        }
        Engineer other = (Engineer) object;
        if ((this.getPersonid() == null && other.getPersonid() != null) || (this.getPersonid() != null && !this.getPersonid().equals(other.getPersonid()))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.Engineer[ personid=" + this.getPersonid() + " ]";
    }
}
