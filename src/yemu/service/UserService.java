package yemu.service;

import yemu.domain.User;

import java.util.List;

public interface UserService {
    User getUserById(int id);
    User getUserByAccount(String account);
    User getUserByName(String name);
    void addUser(User user);
    void deleteUser(User user);
    void update(User user);
    int getCount();
    List<User> getAll();
    void delete(int id);
}
