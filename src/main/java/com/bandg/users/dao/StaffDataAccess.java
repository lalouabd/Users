package com.bandg.users.dao;

import com.bandg.users.RowMappers.StaffRowMapper;
import com.bandg.users.exceptions.Dao.DuplicateStaffException;
import com.bandg.users.exceptions.Dao.InvalidStaffException;
import com.bandg.users.exceptions.Dao.NoSuchStaffException;
import com.bandg.users.models.Staff;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository("postgres")
public class StaffDataAccess implements StaffDao {
    private final JdbcTemplate jdbcTemplate;

    public StaffDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int insertStaff(Staff staff) {
        if ( staff  == null)
            throw new InvalidStaffException("-null-");
        try {
            getStaffById(staff.getId());
                throw new DuplicateStaffException("staff with id " + staff.getId() + " existed");
        }catch (NoSuchStaffException e)
        {

            String sql = "insert into staff values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";

            jdbcTemplate.update(sql,
                    staff.getId(),
                    staff.getBirthDay(),
                    staff.getBirthPlace(),
                    staff.getSex().toString(),
                    staff.getSitFam(),
                    staff.getNationalite(),
                    staff.getDateEmbauche(),
                    staff.getCin(),
                    staff.getSectionAnalytique(),
                    staff.getGrade(),
                    staff.getFunction(),
                    staff.getPost(),
                    staff.getCategory(),
                    staff.getEchelon(),
                    staff.getEntEffect(),
                    staff.getRegimeRetraite(),
                    staff.getAffilRecore(),
                    staff.getDateDerPromo(),
                    staff.getImageId()
            );
            return 1;
        }

    }

    @Override
    public int deleteStaff(int id) {

       getStaffById(id);

       String sql = "delete from staff where id=?";
       jdbcTemplate.update(sql, id);
        return 1;
    }

    @Override
    public int addStaffImage(int id, String path) {
        String sql = "update staff set image_id=? where id = ?";
        jdbcTemplate.update(sql ,  path, id);
        return 1;
    }

    @Override
    public int updateStaff(Staff staff) {
        getStaffById(staff.getId());
        String sql = "update staff set birth_day=? ," +
                " birth_place=?," +
                "sex =?" +
                ", cin=?" +
                ", sit_fam=?" +
                ", nationalite=?"+
                ",date_embauche=?" +
                ",grade=?"+
                ",functio_n=?" +
                ", post=? " +
                ", categorie=?" +
                ", echelon=?" +
                ",ent_effect=?" +
                ",section_analytique=?" +
                ",regime_retraite=?" +
                ",affil_recore=?" +
                ",date_der_promo=? " +
                ",image_link=?" +
                " where id=?";
            jdbcTemplate.update(sql,
                    staff.getBirthDay(),
                    staff.getBirthPlace(),
                    staff.getSex().toString(),
                    staff.getCin(),
                    staff.getSitFam(),
                    staff.getNationalite(),
                    staff.getDateEmbauche(),
                    staff.getGrade(),
                    staff.getFunction(),
                    staff.getPost(),
                    staff.getCategory(),
                    staff.getEchelon(),
                    staff.getEntEffect(),
                    staff.getSectionAnalytique(),
                    staff.getRegimeRetraite(),
                    staff.getAffilRecore(),
                    staff.getDateDerPromo(),
                    staff.getImageId(),
                    staff.getId());
        return 1;
    }

    @Override
    public Staff getStaffById(int id) {
        String sql = "select * from staff where id =?";
        Staff st = null;
        try{
            st =     jdbcTemplate.queryForObject(sql,new StaffRowMapper(),id);
            }catch(EmptyResultDataAccessException e)
            {
                throw new NoSuchStaffException("no such staff with id=" + id);

            }
        return st;
    }

    @Override
    public List<Staff> searchForStaff(String element) {
        element = "%" + element + "%";
     String sql = "select * from staff where" +
             " id::text like ? " +
             "or " +
             " cin like ? " +
             "or " +
             " sit_fam like ?" +
             " or " +

             " birth_place like ? " +
             " or "+
             " post like   ?";
            try{
                List<Staff > staffs = jdbcTemplate.query(sql , new StaffRowMapper(),
                        element
                        , element
                        , element
                        , element
                        ,element


                    );
                return staffs;
            }catch (Exception e)
            {
                e.printStackTrace();
            }
        return  new ArrayList<>();
    }
}
