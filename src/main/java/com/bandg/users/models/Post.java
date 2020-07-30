package com.bandg.users.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.util.UUID;

public class Post {

    @JsonProperty("service")
    private final String service;

    @JsonProperty("city")
    private final String City;

    @JsonProperty("startDate")
    private final Date startDate;
    private  final UUID id;


    public Post(String service, String city, Date startDate, UUID id) {
        this.service = service;
        City = city;
        this.startDate = startDate;
        this.id = id;
    }

    public String getService() {
        return service;
    }

    public String getCity() {
        return City;
    }

    public Date getStartDate() {
        return startDate;
    }
}
