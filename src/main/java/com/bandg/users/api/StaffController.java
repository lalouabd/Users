package com.bandg.users.api;

import com.bandg.users.exceptions.Dao.NoSuchStaffException;
import com.bandg.users.models.Staff;
import com.bandg.users.models.Unrar;
import com.bandg.users.service.StaffService;
import com.fasterxml.jackson.annotation.JsonProperty;

import com.sun.istack.internal.NotNull;
import org.apache.poi.openxml4j.opc.ContentTypes;
import org.apache.poi.openxml4j.opc.internal.ContentType;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.assertj.core.util.Lists;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

@RestController("/api")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
    @PreAuthorize("hasAuthority('write')")
    @PostMapping("/api/addbyexcel")
    public ResponseEntity insertStaffByExcel(@RequestBody() MultipartFile file){
        String res = "done";
        try {
            System.out.println("start");
           res = staffService.insertByExcel(file).toString();
        }catch (Exception e)
        {
            return  ResponseEntity.status(600).body(e.toString());
        }
            return ResponseEntity.status(200).body(res);
    }
    @PreAuthorize("hasAuthority('read')")
    @GetMapping("/api/getStaff/{id}")
    public ResponseEntity getStaffById(@PathVariable("id") int id)
    {
        Staff staff =null;
        try {
             staff = staffService.getStaffById(id);

        }catch (Exception e)
        {
            return ResponseEntity.status(404).body(e.getMessage());
        }
        return ResponseEntity.status(200).body(staff);
    }
    @PreAuthorize("hasAuthority('write')")
        @DeleteMapping("/api/deleteStaff/{id}")
    public ResponseEntity deleteStaffById(@PathVariable("id") int id){

        try {
            staffService.deleteStaffById(id);
        }catch(Exception e)
        {
            return ResponseEntity.status(404).body(e.getMessage());

        }
        return ResponseEntity.status(200).body("staff(id =" + id + ") Deleted successfully");
    }
    @PreAuthorize("hasAuthority('write')")
    @PostMapping(
            value = "/api/addSingleStaff",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity insertStaff(@RequestBody @NotNull  Staff staff)
    {
        String res = "done";
        try {

           staffService.insertSingleStaff(staff);
        }catch (Exception e)
        {
            return  ResponseEntity.status(600).body(e.toString());
        }
        return ResponseEntity.status(200).body(res);
    }

    @PreAuthorize("hasAuthority('write')")
    @PutMapping(value = "/api/updateStaff",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateStaff(@RequestBody @NotNull  Staff staff){
        try {
            staffService.updateStaff(staff);
        }catch (Exception e)
        {
            e.printStackTrace();
            if (e instanceof NoSuchStaffException)
              return  ResponseEntity.status(404).body(e.toString());
            return ResponseEntity.status(600).body(e.toString());
        }
        return ResponseEntity.status(200).body("staff (id: " +staff.getId() +") updated successfully");
    }
    @PreAuthorize("hasAuthority('read')")
    @GetMapping(
            value = "/api/search/{element}")
    public List<Staff> search (@PathVariable("element") String element)
    {

        return staffService.searchForStaff(element);
    }

    @PreAuthorize("hasAuthority('write')")
    @PutMapping("/api/staff/setImage")
    public  ResponseEntity SetStaffImage(@RequestBody() MultipartFile file) {
        List<String> zipType = Lists.list(".zip",
                "application/octet-stream",
                "application/zip"
                ,"application/x-zip",
                "application/x-zip-compressed");

        List<String > imageType = Lists.list(
                ContentTypes.IMAGE_GIF,ContentTypes.IMAGE_JPEG,
                ContentTypes.IMAGE_PNG,ContentTypes.EXTENSION_GIF,
                ContentTypes.EXTENSION_JPG_1, ContentTypes.EXTENSION_JPG_2,
                ContentTypes.EXTENSION_PNG
        );
        JSONObject ret = new JSONObject();

        if (zipType.stream().filter(c-> c.equals(file.getContentType())).findAny().isPresent())
        {
            try {
                File tmp = new File("/opt/Bonedata/tmp");
                tmp.mkdirs();
                file.transferTo(Paths.get(tmp.getAbsolutePath()
                        +"/" +
                        file.getOriginalFilename()));

                File p = new File (tmp.getAbsolutePath()
                        +"/" +
                        file.getOriginalFilename());

                if (p.exists()) {
                    Unrar.extract(p, tmp.getPath());
                    p.delete();
                    for (File img : tmp.listFiles())
                    {
                        try {
                            staffService.inserStaffImage(Files.readAllBytes(Paths.get(img.getAbsolutePath())), img.getName());
                        }catch(Exception e)
                        {
                            ret.put("rejected", e.toString());
                        }
                        img.delete();
                    }

                }
            } catch (Exception e) {
                return ResponseEntity.status(600).body("error Parsing file");
            }


        }
        else {
            return ResponseEntity.status(400).body("File format incorrect");
        }


            return ResponseEntity.status(200).body(ret.toString());
    }


}
