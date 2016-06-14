package de.gedoplan.webclients.jsf.system;

import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerFactory;

public class ApplicationExceptionHandlerFactory extends ExceptionHandlerFactory {

    private ExceptionHandlerFactory parent;

    public ApplicationExceptionHandlerFactory(ExceptionHandlerFactory parent) {
        this.parent = parent;
    }

    @Override
    public ExceptionHandler getExceptionHandler() {

        ExceptionHandler handler = new ApplicationExceptionHandler(parent.getExceptionHandler());

        return handler;
    }

}
