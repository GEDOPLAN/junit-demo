package de.gedoplan.webclients.model;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{

    private String login;

    private List<String> roles;

    private String customerID;

    public boolean isInRole(UserRole role) {
        return this.roles.contains(role.getRolename());
    }

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public static enum UserRole {
        ADMIN(UserRole.ADMINS),
        CUSTOMER(UserRole.CUSTOMERS);
        
        public static final String ADMINS="admin";
        public static final String CUSTOMERS="customer";

        private String rolename;

        private UserRole(String rolename) {
            this.rolename = rolename;
        }

        public String getRolename() {
            return rolename;
        }

        public void setRolename(String rolename) {
            this.rolename = rolename;
        }
    }
}
