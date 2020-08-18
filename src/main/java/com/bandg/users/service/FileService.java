package com.bandg.users.service;

import com.bandg.users.config.FileConfig;
import com.bandg.users.dao.FileDao;
import com.bandg.users.exceptions.parsing.IllegalCellException;
import com.bandg.users.models.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.UUID;

@Service
@ConfigurationProperties("file.datasource")
public class FileService {
    private  final FileDao fileDao;
    private final FileConfig fileConfig;
    @Autowired
    public FileService(FileDao fileDao, FileConfig fileConfig) {
        this.fileDao = fileDao;
        this.fileConfig = fileConfig;
    }

    public UUID insertFile(byte[] file, String name){

        String path = fileConfig.getPrefix() + name;
       try{
        FileOutputStream  out= new FileOutputStream(new File(path));
        out.write(file);
        out.flush();}
       catch (Exception e)
       {
           throw  new IllegalStateException("file could not be saved");
       }
        return fileDao.insertFile(path);
    }
    public MyFile getFileBytesById(UUID id)
    {
        return fileDao.getFileById(id);
    }

    public void deleteFile(UUID imageId) {
        if (
                !imageId.equals(UUID.fromString(
                        fileConfig.getFDef().get(0))
                )
                &&
                !imageId.equals(UUID.fromString(
                        fileConfig.getMDef().get(0))) )
        {
            try {
                fileDao.deleteFileById(imageId);
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
