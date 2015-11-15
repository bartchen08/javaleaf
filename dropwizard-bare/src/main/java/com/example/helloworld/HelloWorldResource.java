package com.example.helloworld;

import com.codahale.metrics.annotation.Timed;

import com.google.common.base.Optional;
import io.dropwizard.jersey.caching.CacheControl;
import io.dropwizard.jersey.params.DateTimeParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;


import java.util.ArrayList;
import java.util.List;
import com.example.helloworld.customer.Customer;
import com.example.helloworld.customer.CustomerContainer;


@Path("/hello-world")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource {
    private static final Logger LOGGER = LoggerFactory.getLogger(HelloWorldResource.class);

    private final Template template;
    private final AtomicLong counter;

    public HelloWorldResource(Template template) {
        this.template = template;
        this.counter = new AtomicLong();
    }

    @GET
    @Timed(name = "get-requests")
    @CacheControl(maxAge = 1, maxAgeUnit = TimeUnit.DAYS)
    public Saying sayHello(@QueryParam("name") Optional<String> name) {
        return new Saying(counter.incrementAndGet(), template.render(name));
    }

    @POST
    public void receiveHello(@Valid Saying saying) {
        LOGGER.info("Received a saying: {}", saying);
    }

    @GET
    @Path("/date")
    @Produces(MediaType.TEXT_PLAIN)
    public String receiveDate(@QueryParam("date") Optional<DateTimeParam> dateTimeParam) {
        if (dateTimeParam.isPresent()) {
            final DateTimeParam actualDateTimeParam = dateTimeParam.get();
            LOGGER.info("Received a date: {}", actualDateTimeParam);
            return actualDateTimeParam.get().toString();
        } else {
            LOGGER.warn("No received date");
            return null;
        }
    }
    
    @GET
    @Path("/listCustomers")
    public CustomerContainer listCustomers(@Context HttpServletResponse response) {
    	response.setHeader("Access-Control-Allow-Origin", "*"); // allow local XHR request.
    	CustomerContainer cuslist = new CustomerContainer();
    	List<Customer> records = new ArrayList<Customer>();
    	cuslist.setRecords(records);
    	records.add(new Customer("Alfreds Futterkiste","Berlin", "Germany"));
    	records.add(new Customer("Tom Horn","London","UK"));
        return cuslist;
    }
}
