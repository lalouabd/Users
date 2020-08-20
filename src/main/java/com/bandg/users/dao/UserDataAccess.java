package com.bandg.users.dao;

import com.bandg.users.RowMappers.UserRowMapper;
import com.bandg.users.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.bandg.users.security.ApplicationUserRole.REGULARUSER;


@Repository("userPostgres")
public class UserDataAccess  implements UserDao{

    private  final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDataAccess(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
        public int insertUser(User user) {
            String sql = "insert into usertable values(?,?,?,?,?,?,?,?,?,?)";
        System.out.println("insert user" + user.getUsername());

            jdbcTemplate.update(sql,
                    user.getUsername(),
                    user.getFullName(),
                    user.getPost(),
                    user.getDob(),
                    user.getPassword(),
                    user.getGender().toString(),
                    user.isEnabled() ? 1:0,
                    user.isAccountNonLocked()?1:0,
                    user.getAuthorities().containsAll(REGULARUSER.getGrantedAuthorities())?0:1,
                    user.getImageId()
                    );
            return 1;
        }

    @Override
    public int deleteUserByEmail(String Email) {
        String sql = "delete from usertable where email =?";
        jdbcTemplate.update(sql ,Email);
        return 0;
    }

    @Override
    public UserDetails loadUserByUsername(String Email) {
         return loadUser(Email);
    }

    @Override
    public User loadUser(String Email) {
        String sql = "select * from usertable where email = ?";
        return jdbcTemplate.queryForObject(sql , new UserRowMapper(), Email);
    }

    @Override
    public int activateAccount(String Email) {
        return 0;
    }

    @Override
    public int desActivateAccount(String Email) {
        return 0;
    }

    @Override
    public int lockAccount(String Email, boolean status) {
        return 0;
    }

    @Override
    public List<User> search(String keyword) {
        return null;
    }

    @Override
    public int setUserAsAdmin(String email) {
        return 0;
    }

    @Override
    public int updateUser(User user) {
        return 0;
    }

    @Override
    public List<User> getAllUsers(String lastOneEmail) {
        return jdbcTemplate.query("select * from usertable" , new UserRowMapper());
    }
}
