package com.bandg.users.RowMappers;

import com.bandg.users.models.Gender;
import com.bandg.users.models.Staff;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class StaffRowMapper implements RowMapper<Staff> {

    @Override
    public Staff mapRow(ResultSet result, int i) throws SQLException {
       if (result.isClosed())
           return null;
        Staff stf = new Staff(
               result.getInt("id"),
               result.getDate("birth_day"),
               result.getString("birth_place"),
               result.getString("sex").equals("MALE") ?
                       Gender.MALE:
                       Gender.FEMALE,
               result.getString("cin"),
               result.getString("sit_fam"),
               result.getString("nationalite"),
               result.getString("date_embauche"),
               result.getString("grade"),
               result.getString("functio_n"),
               result.getString("post"),
               result.getString("categorie"),
               result.getInt("echelon"),
               result.getString("ent_effect"),
               result.getString("section_analytique"),
               result.getString("regime_retraite"),
               result.getInt("affil_recore"),
               result.getString("date_der_promo"),
              UUID.randomUUID().fromString(
                      result.getString("image_link")
              )
       );
        return stf;
    }
}
