package de.gedoplan.webclients.system.security;

import javax.ejb.EJBAccessException;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class AccessExceptionHandler implements ExceptionMapper<EJBAccessException> {

    @Context
    HttpServletRequest request;

    @Override
    public Response toResponse(EJBAccessException exception) {
        if (request.getUserPrincipal()==null){
            //User is not logged in
            return Response.status(455).type("text/html").build();
            
        }else
        {
            //User is not allowed
            return Response.status(456).type("text/html").build();
        }
    }
}
