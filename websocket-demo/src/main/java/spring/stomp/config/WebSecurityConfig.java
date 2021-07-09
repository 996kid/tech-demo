package spring.stomp.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
             // 设置 SpringSecurity 对 / 和 "/login" 路径不拦截
            .mvcMatchers("/demo", "/chat", "/hello", "/", "/login").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            // 设置 Spring Security 的登录页面访问路径为/login
            .loginPage("/login")
            // 登录成功后转向 /chat 路径
            .defaultSuccessUrl("/chat")
            .permitAll()
            .and()
            .logout()
            .permitAll();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            // 在内存中分配两个用户 nasus 和 chenzy ，用户名和密码一致
            // BCryptPasswordEncoder() 是 Spring security 5.0 中新增的加密方式
            // 登陆时用 BCrypt 加密方式对用户密码进行处理。
            .passwordEncoder(new BCryptPasswordEncoder())
            .withUser("nasus")
            // 保证用户登录时使用 bcrypt 对密码进行处理再与内存中的密码比对
            .password(new BCryptPasswordEncoder().encode("nasus")).roles("USER")
            .and()
            // 登陆时用 BCrypt 加密方式对用户密码进行处理。
            .passwordEncoder(new BCryptPasswordEncoder())
            .withUser("chenzy")
            // 保证用户登录时使用 bcrypt 对密码进行处理再与内存中的密码比对
            .password(new BCryptPasswordEncoder().encode("chenzy")).roles("USER");
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 访问 /** 目录下的静态资源，Spring Security 不拦截
        web.ignoring().antMatchers("/**");
    }
}