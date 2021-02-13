package app.service.impl;

import app.bean.Role;
import app.bean.User;
import app.mapper.UserMapper;
import app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public User loadUserByUsername(String username) {
        User user =  userMapper.loadUserByUsername(username);
        if(null==username){
            throw new UsernameNotFoundException("用户名不存在!");
        }
        user.setRoles(userMapper.getUserRolesByUid(user.getId()));
        return user;
    }

    @Override
    public List<Role> getUserRolesByUid(Integer id) {
        return userMapper.getUserRolesByUid(id);
    }
}
