package org.example.server;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import java.util.List;
import java.util.Set;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.example.domain.Person;

/**
 */
@Path("/view")
@Produces(MediaType.APPLICATION_JSON)
public class ViewStorage {


    private Set<Person> storage;

    @Inject
    public ViewStorage(@Named("storage")Set<Person> storage) {
        this.storage = storage;
    }

    @GET
    public List<Person> getAll(){
        return Lists.newArrayList(storage);
    }
}
