package yemu.mapper;

import yemu.domain.User;
public interface UserMapper {
    User getUserById(int id);
    User getUserByAccount(String account);
    User getUserByName(String name);
    int insertUser(User user);
    void updateUser(User user);
    void deleteUser(User user);
}
