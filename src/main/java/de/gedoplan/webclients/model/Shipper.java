package de.gedoplan.webclients.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "shippers")
public class Shipper implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer shipperID;

    private String companyName;

    private String phone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "shipVia")
    private Collection<Order> orders;

    public Shipper() {
    }

    public Shipper(Integer shipperID) {
        this.shipperID = shipperID;
    }

    public Shipper(Integer shipperID, String companyName, String phone) {
        this.shipperID = shipperID;
        this.companyName = companyName;
        this.phone = phone;
    }

    public Integer getShipperID() {
        return shipperID;
    }

    public void setShipperID(Integer shipperID) {
        this.shipperID = shipperID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (shipperID != null ? shipperID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Shipper)) {
            return false;
        }
        Shipper other = (Shipper) object;
        if ((this.shipperID == null && other.shipperID != null) || (this.shipperID != null && !this.shipperID.equals(other.shipperID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.gedoplan.angular.rw.model.Shippers[ shipperID=" + shipperID + " ]";
    }

}
