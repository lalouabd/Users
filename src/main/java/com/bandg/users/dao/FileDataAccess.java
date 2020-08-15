package com.bandg.users.dao;

import com.bandg.users.models.MyFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class FileDataAccess  implements FileDao{
    public  final JdbcTemplate jdbcTemplate;

    @Autowired
    public FileDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UUID insertFile( String name) {
            UUID id = UUID.randomUUID();
            String sql = "insert into file values(?,?);";
            jdbcTemplate.update(sql , id ,name);
            return id ;
    }

    @Override
    public MyFile getFileById(UUID id) {
        String sql = "select  *  from file where id = ?";

        return jdbcTemplate.queryForObject(sql ,
                  (resultSet, i) -> {

                    String  path = resultSet.getString("path");
                    UUID fileId = UUID.fromString(resultSet.getString("id"));

                    return  new MyFile(fileId, path);
        }
                ,
                id);
    }
}
