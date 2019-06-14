package yemu.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import yemu.domain.User;
import yemu.mapper.UserMapper;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SqlSessionFactory sqlSessionFactory;
    @Override
    public User getUserById(int id) {
//        return this.userDao.getUserById(id);
        return userMapper.getUserById(id);
    }

    @Override
    public User getUserByAccount(String account) {
        return userMapper.getUserByAccount(account);
    }

    @Override
    public User getUserByName(String name) {
        User user=userMapper.getUserByName(name);
        return user;
    }

    @Override
    public void addUser(User user){
        if (userMapper.getUserByName(user.getName())==null&&userMapper.getUserByAccount(user.getAccount())==null){
            userMapper.insertUser(user);
        }
    }

    @Override
    public void deleteUser(User user) {

    }

    @Override
    public void updateUser(User user) {

    }

}
