package org.example.server;

import com.google.common.collect.Sets;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.google.inject.TypeLiteral;
import com.google.inject.name.Names;
import java.util.Set;
import org.example.domain.Person;
import org.example.domain.PersonService;

/**
 */
public class ServerModule implements Module{
    public void configure(Binder binder) {
        binder.bind(new TypeLiteral<Set<Person>>(){}).annotatedWith(Names.named("storage")).toInstance(Sets.<Person>newConcurrentHashSet());
        binder.bind(PersonService.class).to(PersonServiceImpl.class);
    }
}
