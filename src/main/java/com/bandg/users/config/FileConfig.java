package com.bandg.users.config;

import com.bandg.users.models.MyFile;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.UUID;

@Configuration
@ConfigurationProperties(prefix = "file")
@EnableConfigurationProperties
@NoArgsConstructor
public class FileConfig {

    private List<String > FDef;
   private List<String> MDef;

    private String prefix;




    public List<String> getFDef() {
        return FDef;
    }

    public void setFDef(List<String> FDef) {
        this.FDef = FDef;
    }

    public List<String> getMDef() {
        return MDef;
    }

    public void setMDef(List<String> MDef) {
        this.MDef = MDef;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
