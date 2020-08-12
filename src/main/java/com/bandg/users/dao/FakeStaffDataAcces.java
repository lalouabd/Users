package com.bandg.users.dao;

import com.bandg.users.models.Staff;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
@Repository
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
       db.removeIf(st->st.getId() == id);
        return 1;
    }

    @Override
    public int addStaffImage(int id, String path) {
        return 0;
    }

    @Override
    public int updateStaff(Staff staff) {

 Optional<Staff> stf =      db.stream().filter(st-> st.getId() == staff.getId()).findFirst();
          if(stf.isPresent())
          {
              db.remove(stf);
              db.add(staff);
          }
 return 0;
    }

    @Override
    public Staff getStaffById(int id) {
       return db.stream().filter(s->s.getId() == id).findFirst().orElse(null);
    }
}
