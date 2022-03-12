package com.librarymanager.library.service;

import com.librarymanager.library.pojo.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {

    List<User> queryAllUsers();

    User queryUserById(Long id);

    int saveUser(User user);

    int updateUserById(User user);

    int deleteUserById(Long id);

}
