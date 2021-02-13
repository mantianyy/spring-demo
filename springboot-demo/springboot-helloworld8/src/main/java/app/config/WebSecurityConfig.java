package app.config;

import app.service.UserService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);
    //密码解码器
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10);
    }
    @Bean
    RoleHierarchy roleHierarchy(){
        RoleHierarchyImpl roleHierarchy = new RoleHierarchyImpl();
        String hierarchy = "ROLE_ROOT > ROLE_ADMIN > ROLE_DBA > ROLE_USER";
        roleHierarchy.setHierarchy(hierarchy);
        return roleHierarchy;
    }

    @Autowired
    private UserService userService;

    //授权服务
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
        String root = "123";
        String admin = "lizichen";
        String dba = "dba";
        String user = "user";
        System.out.println(passwordEncoder.encode(root));
        System.out.println(passwordEncoder.encode(admin));
        System.out.println(passwordEncoder.encode(dba));
        System.out.println(passwordEncoder.encode(user));
    }
    //认证服务
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .formLogin()
            .loginProcessingUrl("/login")
            //登录成功、失败处理
            .usernameParameter("username")
            .passwordParameter("password")
            .successHandler(new AuthenticationSuccessHandler() {
                @Override
                public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                    //设置返回状态
                    //设置响应状态
                    //返回响应信息
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.setStatus(200);
                    Writer out = httpServletResponse.getWriter();
                    out.write("{\"code:\":\"1\",\"message\":\"登录成功!\"}");
                    out.flush();
                    IOUtils.closeQuietly(out);
                }
                })
            .failureHandler(new AuthenticationFailureHandler() {
                @Override
                public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                    httpServletResponse.setContentType("application/json;charset=UTF-8");
                    httpServletResponse.setStatus(200);
                    Writer out = httpServletResponse.getWriter();
                    String result = "";
                    if (e instanceof AccountExpiredException) {
                        result = "{\"code:\":\"-1\",\"message\":\"登录失败,账户过期!\"}";
                    } else if (e instanceof CredentialsExpiredException) {
                        result = "{\"code:\":\"-1\",\"message\":\"登录失败,账户凭据过期!\"}";
                    } else if (e instanceof DisabledException) {
                        result = "{\"code:\":\"-1\",\"message\":\"登录失败,账户已禁用!\"}";
                    } else if (e instanceof LockedException) {
                        result = "{\"code:\":\"-1\",\"message\":\"登录失败,账户已锁定!\"}";
                    } else if (e instanceof AccountStatusException) {
                        result = "{\"code:\":\"-1\",\"message\":\"登录失败,账户状态异常!\"}";
                    } else {
                        logger.info(e.getLocalizedMessage());
                        result = "{\"code:\":\"-1\",\"message\":\"登录失败!\"}";
                    }
                    out.write(result);
                    out.flush();
                    IOUtils.closeQuietly(out);
                }
            })
            //登出处理
            .and()
            .logout()
            .logoutUrl("/logout")
            //清除授权
            .clearAuthentication(true)
            //使session信息过期
            .invalidateHttpSession(true)
            .logoutSuccessHandler(new LogoutSuccessHandler() {
                @Override
                public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                    httpServletResponse.sendRedirect("/login");
                }
            })
            .and()
            .csrf()
            .disable();
    }
}
