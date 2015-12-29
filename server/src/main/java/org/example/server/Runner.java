package org.example.server;

import com.codahale.metrics.health.HealthCheck;
import com.google.inject.Guice;
import com.google.inject.Injector;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.views.ViewBundle;
import one.nio.net.ConnectionString;
import one.nio.rpc.RpcServer;
import org.example.domain.PersonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 */
public class Runner extends Application<ServerConfiguration> {
    private static final Logger LOGGER = LoggerFactory.getLogger(Runner.class.getName());

    public static void main(String[] args) throws Exception {
        new Runner().run(args);
    }


    @Override
    public String getName() {
        return "Person Service";
    }

    @Override
    public void initialize(Bootstrap<ServerConfiguration> bootstrap) {
        bootstrap.addBundle(new ViewBundle<ServerConfiguration>());
    }

    @Override
    public void run(ServerConfiguration serverConfiguration, Environment environment) throws Exception {
        Injector injector = Guice.createInjector(new ServerModule());
        ConnectionString connectionString = new ConnectionString(serverConfiguration.getConnectionString());
        PersonService ps = injector.getInstance(PersonService.class);
        new RpcServer<PersonService>(connectionString, ps).start();
        LOGGER.info("Service started ");
        //DO NOT SKIP HEALTHCHEKS!!!
        environment.healthChecks().register("default", new HealthCheck() {
            @Override
            protected Result check() throws Exception {
                return Result.healthy();
            }
        });

        environment.jersey().register(injector.getInstance(ViewStorage.class));


    }
}
