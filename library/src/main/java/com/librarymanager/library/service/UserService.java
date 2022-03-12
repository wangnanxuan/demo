package com.librarymanager.library.service;

import com.librarymanager.library.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> queryAllUsers();

    User queryUserById(int id);

    int saveUser(User user);

    int updateUserById(User user);

    int deleteUserById(int id);

}
