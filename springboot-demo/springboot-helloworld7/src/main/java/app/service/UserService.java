package app.service;

import app.bean.Role;
import app.bean.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User loadUserByUsername(String username);

    List<Role> getUserRolesByUid(Integer id);
}
