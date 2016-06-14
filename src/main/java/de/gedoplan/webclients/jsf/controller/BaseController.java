package de.gedoplan.webclients.jsf.controller;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

public interface BaseController {

    public default HttpServletRequest getRequest() {
        return (HttpServletRequest) getExternalContext().getRequest();
    }

    public default void addErrorMessage(String key) {
        this.getFacesContext().addMessage(null, createFacesMessage(FacesMessage.SEVERITY_ERROR, key));
    }

    public default void addErrorMessage(String key, Object[] args) {
        this.getFacesContext().addMessage(null, createFacesMessage(FacesMessage.SEVERITY_ERROR, key, args));
    }

    public default void addErrorMessage(String componentId, String key, Object[] args) {
        this.getFacesContext().addMessage(componentId, createFacesMessage(FacesMessage.SEVERITY_ERROR, key, args));
    }

    public default void addErrorMessage(String componentId, String message) {
        this.getFacesContext().addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_ERROR, message, message));
    }

    public default void addInfoMessage(String key) {
        this.getFacesContext().addMessage(null, createFacesMessage(FacesMessage.SEVERITY_INFO, key));
    }

    public default void addInfoMessage(String key, Object[] args) {
        this.getFacesContext().addMessage(null, createFacesMessage(FacesMessage.SEVERITY_INFO, key, args));
    }

    public default void addInfoMessage(String componentId, String key, Object[] args) {
        this.getFacesContext().addMessage(componentId, createFacesMessage(FacesMessage.SEVERITY_INFO, key, args));
    }

    public default void addInfoMessage(String componentId, String message) {
        this.getFacesContext().addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_INFO, message, message));
    }

    public default void addWarnMessage(String key) {
        this.getFacesContext().addMessage(null, createFacesMessage(FacesMessage.SEVERITY_WARN, key));
    }

    public default void addWarnMessage(String key, Object[] args) {
        this.getFacesContext().addMessage(null, createFacesMessage(FacesMessage.SEVERITY_WARN, key, args));
    }

    public default void addWarnMessage(String componentId, String key, Object[] args) {
        this.getFacesContext().addMessage(componentId, createFacesMessage(FacesMessage.SEVERITY_WARN, key, args));
    }

    public default void addWarnMessage(String componentId, String message) {
        this.getFacesContext().addMessage(componentId, new FacesMessage(FacesMessage.SEVERITY_WARN, message, message));
    }

    public default FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public default ExternalContext getExternalContext() {
        return this.getFacesContext().getExternalContext();
    }

    public default FacesMessage createFacesMessage(FacesMessage.Severity severity, String key, Object... args) {
        String msg = getMessage(key, args);
        return new FacesMessage(severity, msg, "");
    }

    default String getMessage(String key, Object...args) {
        return MessageFormat.format(getBundle().getString(key), args);
    }

    public default ResourceBundle getBundle() {
        FacesContext context = FacesContext.getCurrentInstance();
        return context.getApplication().getResourceBundle(context, "msg");
    }

    public default Locale getLocale() {
        return getFacesContext().getViewRoot().getLocale();
    }

}
