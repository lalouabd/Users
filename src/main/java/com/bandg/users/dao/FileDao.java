package com.bandg.users.dao;

import com.bandg.users.models.MyFile;

import java.util.UUID;

public interface FileDao {
    UUID insertFile( String name);
  MyFile getFileById(UUID id);
}
