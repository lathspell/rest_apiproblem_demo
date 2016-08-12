package de.lathspell.rest.apiproblem.demo.server;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;
import static javax.ws.rs.core.Response.Status.BAD_REQUEST;

import de.lathspell.rest.apiproblem.exceptions.ApiProblemException;

@Path("/calc")
public class CalcResource {

    /** Can throw Division By Zero. */
    @GET
    @Path("/{a: [0-9]+}:{b: [0-9]+}")
    @Produces(TEXT_PLAIN)
    public int divide(@PathParam("a") int a, @PathParam("b") int b) {
        return a / b;
    }

    @GET
    @Path("/exception")
    @Produces(TEXT_PLAIN)
    public void exception() {
        throw new RuntimeException("This is a runtime exception!");
    }

    @GET
    @Path("/problem")
    @Produces(TEXT_PLAIN)
    public void problem() {
        throw new ApiProblemException(BAD_REQUEST, "Strange problem!");
    }

}
