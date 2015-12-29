package org.example.client;

import java.lang.reflect.Proxy;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import one.nio.net.ConnectionString;
import one.nio.rpc.RpcClient;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.example.domain.Person;
import org.example.domain.PersonService;

/**
 */
public class ClientRunner {

    private static final AtomicInteger ID_COUNTER = new AtomicInteger();

    public static void main(String[] args) {


        ConnectionString connectionString = new ConnectionString(args[0]);

        RpcClient h = new RpcClient(connectionString);
        PersonService client = (PersonService) Proxy.newProxyInstance(
                PersonService.class.getClassLoader(),
                new Class[]{PersonService.class},
                h);

        long start = System.currentTimeMillis();
        Person person;
        do {
            person = createPerson();
            client.addPerson(person);
        } while (System.currentTimeMillis() - start <= 1000);


        List<Person> all = client.getAll();
        System.out.println(all.size());
    }

    private static Person createPerson() {
        int age = RandomUtils.nextInt(60);
        String name = RandomStringUtils.randomAlphabetic(6);
        String lastname = RandomStringUtils.randomAlphabetic(8);
        return new Person(age, name, lastname, ID_COUNTER.incrementAndGet());
    }
}
