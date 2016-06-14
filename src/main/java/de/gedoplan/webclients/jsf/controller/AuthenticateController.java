package de.gedoplan.webclients.jsf.controller;

import java.security.Principal;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.servlet.ServletException;

@Named
@RequestScoped
public class AuthenticateController implements BaseController {

    private String loginname;

    private String password;

    public String login() {
        try {
            Principal userPrincipal = BaseController.super.getRequest().getUserPrincipal();
            if (userPrincipal==null) {
                BaseController.super.getRequest().login(loginname, password);
            }
        } catch (ServletException ex) {
            addErrorMessage("login_invalid");
            return null;
        }
        return "home";
    }

    public String logout() {
        getExternalContext().invalidateSession();
        return "login";
    }

    public String getLoginname() {
        return loginname;
    }

    public void setLoginname(String loginname) {
        this.loginname = loginname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
