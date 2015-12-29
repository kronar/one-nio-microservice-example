package org.example.server;

import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by nikita on 19.11.15.
 */
public class ServerConfiguration extends Configuration {
    @NotBlank
    private String connectionString;

    public String getConnectionString() {
        return connectionString;
    }

}
