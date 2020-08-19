package com.bandg.users.RowMappers;

import com.bandg.users.models.Staff;
import com.bandg.users.models.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return null  ;
    }
}
