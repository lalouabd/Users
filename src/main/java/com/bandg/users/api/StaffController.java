package com.bandg.users.api;

import com.bandg.users.exceptions.Dao.NoSuchStaffException;
import com.bandg.users.models.Staff;
import com.bandg.users.service.StaffService;
import com.sun.istack.internal.NotNull;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController("/api")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
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
    @PutMapping(value = "/api/updateStaff",
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateStaff(@RequestBody @NotNull  Staff staff){
        try {
            staffService.updateStaff(staff);
        }catch (Exception e)
        {
            if (e instanceof NoSuchStaffException)
              return  ResponseEntity.status(404).body(e.toString());
            return ResponseEntity.status(600).body(e.toString());
        }
        return ResponseEntity.status(200).body("staff (id: " +staff.getId() +") updated successfully");
    }
    @GetMapping(
            value = "/api/search/{element}")
    public List<Staff> search (@PathVariable("element") String element)
    {
        return staffService.searchForStaff(element);
    }

}
