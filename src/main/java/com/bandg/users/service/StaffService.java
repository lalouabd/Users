package com.bandg.users.service;

import com.bandg.users.dao.StaffDao;
import com.bandg.users.models.Staff;
import com.bandg.users.models.StaffParser;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class StaffService {
    private  final StaffDao staffDao;
    private  final StaffParser staffParser;

    @Autowired
    public StaffService(StaffDao staffDao, StaffParser staffParser) {
        this.staffDao = staffDao;
        this.staffParser = staffParser;
    }

    public JSONArray insertByExcel(MultipartFile file) throws Exception
    {

        byte[] fileBytes = file.getBytes();
        System.out.println("inside service");
        List<Object> objectList = staffParser.parseFile(fileBytes);
        System.out.println("nothing");

        ( (List<Staff>) objectList.get(0)).forEach(staffDao::insertStaff);
        return ((JSONArray)objectList.get(1));
    }

    public Staff getStaffById(int id) {
        return staffDao.getStaffById(id);
    }

    public int deleteStaffById(int id) {
        return staffDao.deleteStaff(id);
    }

    public int insertSingleStaff(Staff staff) {
        return staffDao.insertStaff(staff);
    }
}
