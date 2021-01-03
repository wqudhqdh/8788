package cn.itcast.Store.service.impl;

import cn.itcast.Store.Dao.UserDao;
import cn.itcast.Store.Dao.impl.UserDaoImpl;
import cn.itcast.Store.Domain.User;
import cn.itcast.Store.service.UserService;

public class UserServiceImpl  implements UserService {
    UserDao userDao=new UserDaoImpl();
    @Override
    public boolean register(User user) {//注册

          boolean flag=userDao.isRegister(user);
        return flag;
    }

    @Override
    public void save(User user) {//保存

        userDao.save( user);
    }

    @Override
    public User Login(String email, String password) {//登录

       User user=userDao.login(email,password);
       return user;
    }

    @Override
    public void updatePassword(String username, String newPassword) {
        userDao.updatePassword(username,newPassword);
    }
}
