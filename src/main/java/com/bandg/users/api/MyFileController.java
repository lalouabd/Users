package com.bandg.users.api;

import com.bandg.users.models.MyFile;
import com.bandg.users.service.FileService;
import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.MediaTypeFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@RestController
public class MyFileController {
    public final FileService fileService;


    public MyFileController(FileService fileService) {
        this.fileService = fileService;
    }
    @PreAuthorize("hasAuthority('write')")
    @GetMapping("/api/download/{id}")
    public ResponseEntity downloadFile(@PathVariable("id") UUID id) {
        MyFile file = fileService.getFileBytesById(id);
        String filename  = file.getPath().substring(file.getPath().lastIndexOf("/"));
        MediaType mediaType = MediaTypeFactory.getMediaType( filename).get();



        Path path = Paths.get(file.getPath());
        try {
            byte[] data = Files.readAllBytes(path);
            ByteArrayResource resource = new ByteArrayResource(data);

            return ResponseEntity.ok()
                    // Content-Disposition
                    .header(HttpHeaders.CONTENT_DISPOSITION,
                            "attachment;filename=" + path.getFileName().toString())
                    // Content-Type
                    .contentType(mediaType) //
                    // Content-Lengh
                    .contentLength(data.length) //
                    .body(resource);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }

    }
    
    
    
    
    
}