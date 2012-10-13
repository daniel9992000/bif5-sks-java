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
@Table(name = "ENGINEER")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Engineer.findAll", query = "SELECT e FROM Engineer e"),
    @NamedQuery(name = "Engineer.findByEngineerid", query = "SELECT e FROM Engineer e WHERE e.engineerid = :engineerid"),
    @NamedQuery(name = "Engineer.findByFirstname", query = "SELECT e FROM Engineer e WHERE e.firstname = :firstname"),
    @NamedQuery(name = "Engineer.findByLastname", query = "SELECT e FROM Engineer e WHERE e.lastname = :lastname"),
    @NamedQuery(name = "Engineer.findByUsername", query = "SELECT e FROM Engineer e WHERE e.username = :username"),
    @NamedQuery(name = "Engineer.findByPassword", query = "SELECT e FROM Engineer e WHERE e.password = :password"),
    @NamedQuery(name = "Engineer.findByEmail", query = "SELECT e FROM Engineer e WHERE e.email = :email")})
public class Engineer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "ENGINEERID")
    private Integer engineerid;
    @Size(max = 40)
    @Column(name = "FIRSTNAME")
    private String firstname;
    @Size(max = 40)
    @Column(name = "LASTNAME")
    private String lastname;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "USERNAME")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 40)
    @Column(name = "PASSWORD")
    private String password;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 40)
    @Column(name = "EMAIL")
    private String email;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "engineerid")
    private Collection<Customer> customerCollection;

    public Engineer() {
    }

    public Engineer(Integer engineerid) {
        this.engineerid = engineerid;
    }

    public Engineer(Integer engineerid, String username, String password) {
        this.engineerid = engineerid;
        this.username = username;
        this.password = password;
    }

    public Integer getEngineerid() {
        return engineerid;
    }

    public void setEngineerid(Integer engineerid) {
        this.engineerid = engineerid;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @XmlTransient
    public Collection<Customer> getCustomerCollection() {
        return customerCollection;
    }

    public void setCustomerCollection(Collection<Customer> customerCollection) {
        this.customerCollection = customerCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (engineerid != null ? engineerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Engineer)) {
            return false;
        }
        Engineer other = (Engineer) object;
        if ((this.engineerid == null && other.engineerid != null) || (this.engineerid != null && !this.engineerid.equals(other.engineerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "at.heli.scada.entities.Engineer[ engineerid=" + engineerid + " ]";
    }
    
}
