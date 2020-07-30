package com.bandg.users.dao;

import com.bandg.users.models.Staff;

import java.util.ArrayList;
import java.util.List;
public class FakeStaffDataAcces implements  StaffDao {
   private  final List<Staff>  db;
   public FakeStaffDataAcces()
   {
       db = new ArrayList<>();
   }
    @Override
    public int insertStaff(Staff staff) {
       db.add(staff);
       return 1;
    }

    @Override
    public int deleteStaff(int id) {

        return 1;
    }

    @Override
    public int addStaffImage(int id, String path) {
        return 0;
    }

    @Override
    public int updateStaff(Staff staff) {
        return 0;
    }
}
