package cn.itcast.Store.Dao;

import cn.itcast.Store.Domain.User;

public interface UserDao  {
    boolean isRegister(User user);

    void save(User user);

  User login(String email, String password);

    void updatePassword(String username, String newPassword);
}
