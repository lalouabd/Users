package com.bandg.users.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jdk.nashorn.internal.objects.annotations.Property;
import org.springframework.beans.factory.annotation.Value;

import java.sql.Date;
import java.util.UUID;

public class Post {

    @JsonProperty("service")
    private final String service;




    private  final UUID id;


    public Post(String service,  UUID id) {
        this.service = service;
        this.id = id;
    }

    public String getService() {
        return service;
    }


}
