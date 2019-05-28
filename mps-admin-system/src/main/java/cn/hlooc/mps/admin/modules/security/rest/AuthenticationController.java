package cn.hlooc.mps.admin.modules.security.rest;

import cn.hlooc.mps.admin.aop.log.Log;
import cn.hlooc.mps.admin.modules.security.security.AuthenticationInfo;
import cn.hlooc.mps.admin.modules.security.security.AuthorizationUser;
import cn.hlooc.mps.admin.modules.security.security.JwtUser;
import cn.hlooc.mps.admin.modules.security.utils.JwtTokenUtil;
import cn.hlooc.mps.admin.utils.EncryptUtils;
import cn.hlooc.mps.admin.utils.SecurityUtils;
import cn.hlooc.mps.admin.modules.security.security.AuthenticationInfo;
import cn.hlooc.mps.admin.modules.security.security.AuthorizationUser;
import cn.hlooc.mps.admin.modules.security.security.JwtUser;
import cn.hlooc.mps.admin.modules.security.utils.JwtTokenUtil;
import cn.hlooc.mps.admin.utils.EncryptUtils;
import cn.hlooc.mps.admin.utils.SecurityUtils;
import lombok.extern.slf4j.Slf4j;
import cn.hlooc.mps.admin.aop.log.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author hlooc
 * @date 2019-05-18 17:51:59
 * 授权、根据token获取用户详细信息
 */
@Slf4j
@RestController
@RequestMapping("auth")
public class AuthenticationController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Resource
    private JwtTokenUtil jwtTokenUtil;

    @Resource(name = "jwtUserDetailsServiceImpl")
    private UserDetailsService userDetailsService;

    /**
     * 登录授权
     *
     * @param authorizationUser 认证用户信息
     * @return 认证结果
     */
    @Log("用户登录")
    @PostMapping(value = "/login")
    public ResponseEntity login(@Validated @RequestBody AuthorizationUser authorizationUser) {
        log.info("authorizationUser:{}", authorizationUser);
        final JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(authorizationUser.getUsername());

        if (!jwtUser.getPassword().equals(EncryptUtils.encryptPassword(authorizationUser.getPassword()))) {
            throw new AccountExpiredException("密码错误");
        }

        if (!jwtUser.isEnabled()) {
            throw new AccountExpiredException("账号已停用，请联系管理员");
        }

        // 生成令牌
        final String token = jwtTokenUtil.generateToken(jwtUser);

        // 返回 token
        return ResponseEntity.ok(new AuthenticationInfo(token, jwtUser));
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping(value = "/info")
    public ResponseEntity getUserInfo() {
        JwtUser jwtUser = (JwtUser) userDetailsService.loadUserByUsername(SecurityUtils.getUsername());
        return ResponseEntity.ok(jwtUser);
    }
}
