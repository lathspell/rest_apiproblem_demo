package de.lathspell.rest.apiproblem.demo.client.jsf;

import java.io.IOException;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import static javax.ws.rs.core.MediaType.TEXT_PLAIN;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import static com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT;

import de.lathspell.rest.apiproblem.ApiProblem;

@Named
@RequestScoped
@Slf4j
public class ClientBacking {

    private static final ObjectMapper iom = new ObjectMapper().enable(INDENT_OUTPUT);

    private final Client client;

    public ClientBacking() throws JsonProcessingException {
        client = ClientBuilder.newClient();
        log.debug("client: {}:\n{}", client, iom.writeValueAsString(client));
    }

    public String call(String path) throws JsonProcessingException, IOException {
        String output = "Calling " + path + ": ";
        try {
            output += client.target(path).request(TEXT_PLAIN).get(String.class);
        } catch (WebApplicationException e) {
            output += " ... got " + e.getClass() + " with Message '" + e.getMessage() + "' and Body: " + e.getResponse().readEntity(ApiProblem.class);
        }
        return output;
    }

}
