package com.bandg.users.service;

import com.bandg.users.dao.StaffDao;
import com.bandg.users.exceptions.Dao.DuplicateStaffException;
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

@Service
public class StaffService {
    private  final StaffDao staffDao;
    private  final StaffParser staffParser;

    @Autowired
    public StaffService(@Qualifier("postgres") StaffDao staffDao, StaffParser staffParser) {
        this.staffDao = staffDao;
        this.staffParser = staffParser;
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
}
