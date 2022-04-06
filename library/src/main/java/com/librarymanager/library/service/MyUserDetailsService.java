package com.librarymanager.library.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.librarymanager.library.mapper.UserMapper;
import com.librarymanager.library.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

@Service("myUserDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    HttpSession session;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);


        if (user == null) {
            throw new UsernameNotFoundException("username or password is err");
        }

        session.setAttribute("user", user);
        //通过用户等级判断角色权限
        List<GrantedAuthority> roles = null;
        if (user.getLevel() == 3) {
            roles = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_LEVEL2,ROLE_LEVEL1");
            return new org.springframework.security.core.userdetails.User(user.getUsername(), encode(user.getPassword()), roles);
        }
        if (user.getLevel() == 2) {
            roles = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_LEVEL2");
            return new org.springframework.security.core.userdetails.User(user.getUsername(), encode(user.getPassword()), roles);
        }
        roles = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_LEVEL1");
        return new org.springframework.security.core.userdetails.User(user.getUsername(), encode(user.getPassword()), roles);

    }

    //加密
    public String encode(String password) {
        return new BCryptPasswordEncoder().encode(password);
    }
}
