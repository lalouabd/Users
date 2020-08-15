package com.bandg.users.models;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Repository;

import java.util.UUID;


public class MyFile {

    private  UUID id;
    private  String path;

    public void setId(UUID id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MyFile(UUID id, String path) {
        this.id = id;
        this.path = path;
    }

    public UUID getId() {
        return id;
    }

    public String getPath(){
        return path;
    }
}
