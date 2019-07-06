package main.yemu.service;

import main.yemu.Common.Response;
import main.yemu.domain.User;

import java.util.List;

public interface UserService {
    Response<User> getUser(Integer id, String name, String account);
    User getUserById(int id);
    User getUserByAccount(String account);
    List<User> getUserByName(String name);
    void addUser(User user);
    void deleteUser(User user);
    void update(User user);
    int getCount();
    List<User> getAll();
    void delete(int id);
    Response<User> login(String account,String password);
}
