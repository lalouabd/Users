package com.bandg.users.RowMappers;

import com.bandg.users.models.Gender;
import com.bandg.users.models.Staff;
import com.bandg.users.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import static com.bandg.users.models.Gender.FEMALE;
import static com.bandg.users.models.Gender.MALE;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet r, int i) throws SQLException {
        System.out.println(r.getString("password"));
        return new User(
                r.getString("email"),
                r.getString("fullname"),
                r.getDate("dob"),
                r.getString("password"),
                r.getString("post"),
                r.getString("gender").equals("MALE")?
                        MALE:
                        FEMALE,
                r.getInt("isActivated"),
                r.getInt("isLocked"),
                r.getInt("isAdmin"),
               UUID.fromString(r.getString("imageid"))
                );
    }
}
