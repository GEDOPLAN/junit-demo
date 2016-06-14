package de.gedoplan.webclients.model;

import com.fasterxml.jackson.annotation.JsonView;
import de.gedoplan.webclients.resource.views.DetailView;
import de.gedoplan.webclients.resource.views.ListView;
import java.io.Serializable;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @JsonView(ListView.class)
    private String customerID;

    @NotNull
    @Size(min = 3)
    @JsonView(ListView.class)
    private String companyName;

    @NotNull
    @Size(min = 3)
    @JsonView(ListView.class)
    private String contactName;

    @JsonView(DetailView.class)
    private String contactTitle;

    @JsonView(ListView.class)
    private String address;

    @JsonView(ListView.class)
    private String city;

    @JsonView(DetailView.class)
    private String region;

    @JsonView(ListView.class)
    private String postalCode;

    @JsonView(DetailView.class)
    private String country;

    @JsonView(DetailView.class)
    private String phone;

    @JsonView(DetailView.class)
    private String fax;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    @JsonView(DetailView.class)
    private Collection<Order> orders;

    public Customer() {
    }

    public Customer(String customerID) {
        this.customerID = customerID;
    }

    public Customer(String customerID, String companyName, String contactName, String contactTitle, String address, String city, String region, String postalCode, String country, String phone, String fax) {
        this.customerID = customerID;
        this.companyName = companyName;
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.address = address;
        this.city = city;
        this.region = region;
        this.postalCode = postalCode;
        this.country = country;
        this.phone = phone;
        this.fax = fax;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String contactTitle) {
        this.contactTitle = contactTitle;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFax() {
        return fax;
    }

    public void setFax(String fax) {
        this.fax = fax;
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
        hash += (customerID != null ? customerID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customer)) {
            return false;
        }
        Customer other = (Customer) object;
        if ((this.customerID == null && other.customerID != null) || (this.customerID != null && !this.customerID.equals(other.customerID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "de.gedoplan.angular.rw.model.Customers[ customerID=" + customerID + " ]";
    }

}
