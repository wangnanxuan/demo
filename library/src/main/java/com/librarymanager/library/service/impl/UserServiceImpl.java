package com.librarymanager.library.service.impl;

import com.librarymanager.library.mapper.UserMapper;
import com.librarymanager.library.pojo.User;
import com.librarymanager.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public List<User> queryAllUsers() {
        return userMapper.selectList(null);
    }

    @Override
    public User queryUserById(int id) {
        return userMapper.selectById(id);
    }

    @Override
    public int saveUser(User user) {
        return userMapper.insert(user);
    }

    @Override
    public int updateUserById(User user) {
        return userMapper.updateById(user);
    }

    @Override
    public int deleteUserById(int id) {
        return userMapper.deleteById(id);
    }
}
