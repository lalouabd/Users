package com.bandg.users.config;

import com.bandg.users.models.MyFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.UUID;

@Configuration
@ConfigurationProperties(prefix = "file.datasource")
public class FileConfig {
    @Value("${file.datasource.FDef.id}")
    private UUID FdefID;
    @Value("${file.datasource.MDef.id}")
    private UUID MdefId;
    @Value("${file.datasource.MDef.path}")
    private String  MdefPath;
    @Value("${file.datasource.FDef.path}")
    private String FdefPath;

    private MyFile FDef;
   private MyFile MDef;

    @Value("${file.datasource.prefix}")
    private String prefix;

   public FileConfig(){
       FDef  = new MyFile(FdefID, FdefPath);
       MDef  = new MyFile(MdefId , MdefPath);

    }

    public MyFile getFDef() {
        return FDef;
    }

    public void setFDef(MyFile FDef) {
        this.FDef = FDef;
    }

    public MyFile getMDef() {
        return MDef;
    }

    public void setMDef(MyFile MDef) {
        this.MDef = MDef;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }



}
