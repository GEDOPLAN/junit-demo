package de.gedoplan.webclients.jsf.controller;

import de.gedoplan.webclients.model.User;
import de.gedoplan.webclients.system.CurrentUser;
import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@SessionScoped
public class UserController implements BaseController, Serializable {

    @Inject
    @CurrentUser
    private User currentUser;

    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }
    
    public boolean isAdmin(){
        return currentUser.isInRole(User.UserRole.ADMIN);
    }
    
    public boolean isCustomer(){
        return currentUser.isInRole(User.UserRole.CUSTOMER);
    }

}
