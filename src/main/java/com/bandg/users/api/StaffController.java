package com.bandg.users.api;

import com.bandg.users.models.Staff;
import com.bandg.users.service.StaffService;
import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController("/api")
public class StaffController {
    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }
    @PostMapping("/api/addbyexcel")
    public String insertStaffByExcel(@RequestBody() MultipartFile file){
        String res = "done";
        try {
            System.out.println("start");
           res = staffService.insertByExcel(file).toString();
        }catch (Exception e)
        {
            return e.toString();
        }
            return res;
    }
    @GetMapping("/api/getStaff/{id}")
    public Staff getStaffById(@PathVariable("id") int id)
    {

        return staffService.getStaffById(id);
    }
        @DeleteMapping("/api/deleteStaff/{id}")
    public int deleteStaffById(@PathVariable("id") int id){
        return staffService.deleteStaffById(id);
    }
    @PostMapping(
            value = "/api/addSingleStaff",
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public int insertStaff( @RequestBody Staff staff)
    {

        return staffService.insertSingleStaff(staff);
    }
}
