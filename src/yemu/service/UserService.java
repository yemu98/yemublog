package yemu.service;

import yemu.domain.User;

public interface UserService {
    User getUserById(int id);
    User getUserByAccount(String account);
    User getUserByName(String name);
    void addUser(User user);
    void deleteUser(User user);
    void updateUser(User user);
}
