package main.yemu.mapper;

import org.apache.ibatis.annotations.Param;
import main.yemu.domain.User;

import java.util.List;

public interface UserMapper {
    List<User> getUser(User user);
    User getUserById(int id);
    User getUserByAccount(String account);
    List<User> getUserByName(@Param("name") String name);
    int insertUser(User user);
    void update(User user);
    void deleteUser(User user);
    int getCount();
    List<User> getAll();
    void delete(int id);
}
