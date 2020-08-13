package com.bandg.users.dao;

import com.bandg.users.models.Staff;

import java.util.List;

public interface StaffDao {

    int insertStaff(Staff staff);

    int deleteStaff(int id);

    int addStaffImage(int id,String path);

    int updateStaff(Staff staff);
    Staff getStaffById(int id);
    List<Staff>  searchForStaff(String element);
}
