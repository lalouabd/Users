package com.bandg.users.dao;

import com.bandg.users.models.Staff;

public interface StaffDao {

    int insertStaff(Staff staff);

    int deleteStaff(int id);

    int addStaffImage(int id,String path);

    int updateStaff(Staff staff);

}
