package com.bandg.users.service;

import com.bandg.users.dao.StaffDao;
import com.bandg.users.models.Staff;
import com.bandg.users.models.StaffParser;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.UUID;

@Service
public class StaffService {
    private  final StaffDao staffDao;
    private  final StaffParser staffParser;
    private  final FileService fileService;

    @Autowired
    public StaffService(@Qualifier("postgres") StaffDao staffDao, StaffParser staffParser, FileService fileService) {
        this.staffDao = staffDao;
        this.staffParser = staffParser;
        this.fileService = fileService;
    }

    public JSONArray insertByExcel(MultipartFile file) throws Exception
    {

        byte[] fileBytes = file.getBytes();
        List<Object> objectList = staffParser.parseFile(fileBytes);

        ( (List<Staff>) objectList.get(0)).forEach(s->{
            try{
                staffDao.insertStaff(s);
            }catch (Exception e)
            {
               // e.printStackTrace();

                    JSONObject jso = new JSONObject();
                    JSONObject st = new JSONObject();
                    try {
                        st.put("id", s.getId());
                        st.put("reason",e.getMessage());
                        jso.put("rejected",st);
                        ((JSONArray)objectList.get(1)).put(jso);
                    } catch (JSONException jsonException) {
                        jsonException.printStackTrace();
                    }



            }        });
        return ((JSONArray)objectList.get(1));
    }

    public Staff getStaffById(int id) throws Exception {
        return staffDao.getStaffById(id);
    }

    public int deleteStaffById(int id) throws Exception {
        return staffDao.deleteStaff(id);
    }

    public int insertSingleStaff(Staff staff) throws Exception{
        return staffDao.insertStaff(staff);
    }

    public int  updateStaff(Staff staff) {
        return staffDao.updateStaff(staff);
    }
    public List<Staff> searchForStaff(String element){
        return staffDao.searchForStaff(element);
    }
    public int updateStaffImage(){
        return 1;
    }

    public int inserStaffImage(byte[] file, String name) {
        int id  = Integer.parseInt(name.trim().split(".")[0]);
        try {
            Staff staff = getStaffById(id);
            UUID imageid = fileService.insertFile(file, name);
            staff.setImageid(imageid);
            updateStaff(staff);

        } catch (Exception e) {
        throw new IllegalArgumentException("cant set image");
        }


        return  1;

    }
}
