package de.lathspell.rest.apiproblem.demo.server;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import lombok.extern.slf4j.Slf4j;

import de.netcologne.utils.Slf4jRestLoggingFilter;

import de.lathspell.rest.apiproblem.server.RestServerExceptionMapper;

@ApplicationPath("/rest")
@Slf4j
public class RestApplication extends Application {

    public RestApplication() {
        log.info("ctor");
    }

    @Override
    public Set<Object> getSingletons() {
        Set<Object> singletons = new HashSet<>();
        singletons.add(new Slf4jRestLoggingFilter("server"));
        return singletons;
    }

    @Override
    public Map<String, Object> getProperties() {
        Map<String, Object> properties = new HashMap<>();
        log.info("Disabling MOXy JSON provider");
        properties.put("jersey.config.server.disableMoxyJson", true); // if Glassfish is used
        return properties;
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> classes = new HashSet<>();
        addRestResourceClasses(classes);
        classes.add(RestServerExceptionMapper.class);
        return classes;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(CalcResource.class);
    }
}
