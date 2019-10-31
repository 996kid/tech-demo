package com.eastwood.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author 996kid
 * @desription
 * @date 2019/10/31
 */
@EnableWebSecurity
public class WebSecurityConfig {

    // @formatter:off
    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("USER")
                .build();
//        UserDetails user1 = User.withUsername("user").password("password").roles("USER").build();
        return  new InMemoryUserDetailsManager(user);
    }
    // @formatter:on
}
