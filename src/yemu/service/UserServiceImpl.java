package yemu.service;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import yemu.Common.Response;
import yemu.domain.User;
import yemu.mapper.UserMapper;

import javax.annotation.Resource;
import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    @Override
    public Response<User> getUser(Integer id, String name, String account) {
        User user=new User();
        if (id!=null&&id!=0){
            user.setId(id);
        }
        if (name!=null&&!name.equals("")){
            user.setName(name);
        }
        if (account!=null&&!account.equals("")){
            user.setAccount(account);
        }
        List<User> users=userMapper.getUser(user);
        if (users.size()>0){
            return Response.createRespBySuccess(users.get(0));
        }else {
            return Response.createRespByErrorMsg("无此用户!");
        }
    }

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
    public List<User> getUserByName(String name) {
        List<User> users=userMapper.getUserByName(name);
        return users;
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
    public void update(User user) {
        userMapper.update(user);
    }

    @Override
    public int getCount() {
        return userMapper.getCount();
    }

    @Override
    public List<User> getAll() {
        return userMapper.getAll();
    }
    @Transactional
    @Override
    public void delete(int id) {
        userMapper.delete(id);
    }

    @Override
    public Response<User> login(String account, String password) {
        User user=userMapper.getUserByAccount(account);
        if (user==null){
            return Response.createRespByErrorMsg("用户不存在！");
        }
        else {
            if (user.getPassword().equals(password)){
                user.setPassword("");
                return Response.createRespBySuccess(user);
            }
            else {
                return Response.createRespByErrorMsg("密码错误!");
            }
        }
    }

}
