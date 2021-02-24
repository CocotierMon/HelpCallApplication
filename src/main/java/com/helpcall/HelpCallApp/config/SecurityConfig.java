package com.helpcall.HelpCallApp.config;

import com.helpcall.HelpCallApp.domain.User;
import com.helpcall.HelpCallApp.repository.UserRepository;
import com.helpcall.HelpCallApp.service.UserSecurityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserSecurityService userSecurityService;
    private final UserRepository userRepo;

    @Autowired
    public SecurityConfig(UserSecurityService userSecurityService, UserRepository userRepo) {
        this.userSecurityService = userSecurityService;
        this.userRepo = userRepo;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userSecurityService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests()
                .and()
                .formLogin().permitAll()
                .and()
                .logout().permitAll()
                .and()
                .csrf().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @EventListener(ApplicationReadyEvent.class)
    public void get() {
        User adm = new User(1L, "adm", passwordEncoder().encode("adm"), "ROLE_ADMIN");
        User inst = new User(2L, "inst", passwordEncoder().encode("inst"), "ROLE_INSTITUTION");
        User vol = new User(3L, "vol", passwordEncoder().encode("vol"), "ROLE_VOLUNTEER");

        userRepo.deleteAll();
        userRepo.save(adm);
        userRepo.save(inst);
        userRepo.save(vol);
    }
}
