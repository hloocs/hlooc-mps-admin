package cn.hlooc.mps.admin.modules.security.service;

import cn.hlooc.mps.admin.exception.BadRequestException;
import cn.hlooc.mps.admin.modules.security.security.JwtUser;
import cn.hlooc.mps.admin.modules.system.domain.Dept;
import cn.hlooc.mps.admin.modules.system.domain.Job;
import cn.hlooc.mps.admin.modules.system.domain.User;
import cn.hlooc.mps.admin.modules.system.service.UserService;
import cn.hlooc.mps.admin.exception.BadRequestException;
import cn.hlooc.mps.admin.modules.security.security.JwtUser;
import cn.hlooc.mps.admin.modules.system.domain.*;
import cn.hlooc.mps.admin.modules.system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

/**
 * @author hlooc
 * @date 2018-11-22
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtPermissionService permissionService;

    @Override
    public UserDetails loadUserByUsername(String username){

        User user = userService.findByName(username);
        if (user == null) {
            throw new BadRequestException("账号不存在");
        } else {
            return createJwtUser(user);
        }
    }

    public UserDetails createJwtUser(User user) {
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getAvatar(),
                user.getEmail(),
                user.getPhone(),
                Optional.ofNullable(user.getDept()).map(Dept::getName).orElse(null),
                Optional.ofNullable(user.getJob()).map(Job::getName).orElse(null),
                permissionService.mapToGrantedAuthorities(user),
                user.getEnabled(),
                user.getCreateTime(),
                user.getLastPasswordResetTime()
        );
    }
}
