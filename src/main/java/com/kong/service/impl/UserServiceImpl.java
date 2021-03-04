package com.kong.service.impl;

import com.kong.dao.UserMapper;
import com.kong.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserServiceImpl implements UserDetailsService, UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        //调用dao层，查询数据库，拿到用户的姓名，密码，权限
        com.kong.pojo.User user = userMapper.getUser();
        if(user==null){
            throw new UsernameNotFoundException("用户不存在");
        }
        //因为没有dao层，用户名、密码、权限这里先写死了，权限不能为空
        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("aaa");
        return new User(user.getName(),new BCryptPasswordEncoder().encode(user.getPassword()),auths);
    }
}
