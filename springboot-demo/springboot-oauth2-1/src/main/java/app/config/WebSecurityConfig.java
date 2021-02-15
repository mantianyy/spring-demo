package app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger logger = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }



    public static void main(String[] args) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String admin = "123";
        String user = "123";
        System.out.println(passwordEncoder.encode(admin));
        System.out.println(passwordEncoder.encode(user));
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin")
            .password("$2a$10$1Hu2xkcGBUWq/H5APfre0.QaaXbjuPAXgdRnltJm6T7SF5p4DZKyG")
            .roles("admin")
            .and()
            .withUser("lizichen")
            .password("$2a$10$233s9HG1M6FcGHJYeZeOI.fI.FB0NPMhabKp0xXssiSOj3KmNEXFC")
            .roles("user");
    }

    //认证服务
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**")
            .authorizeRequests()
            .antMatchers("/oauth/**")
            .permitAll()
            .and()
            .csrf()
            .disable();
    }



}
