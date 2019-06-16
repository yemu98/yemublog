package yemu.mapper;

import yemu.domain.User;

import java.util.List;

public interface UserMapper {
    User getUserById(int id);
    User getUserByAccount(String account);
    User getUserByName(String name);
    int insertUser(User user);
    void update(User user);
    void deleteUser(User user);
    int getCount();
    List<User> getAll();
    void delete(int id);
}
