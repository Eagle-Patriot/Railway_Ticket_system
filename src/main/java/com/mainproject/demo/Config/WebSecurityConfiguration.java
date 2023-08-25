package com.mainproject.demo.Config;

import com.mainproject.demo.Tripmate.LoginService;
import com.mainproject.demo.Tripmate.Repository.TripmateRepository;
import com.mainproject.demo.Tripmate.Service.TripmateService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import static com.mainproject.demo.Config.Permission.*;
import static com.mainproject.demo.Config.UserRole.ADMIN;
import static com.mainproject.demo.Config.UserRole.USER;
import static org.springframework.http.HttpMethod.*;

//initialise the class as the web security configuration
@EnableMethodSecurity
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class WebSecurityConfiguration{

    private final TripmateRepository tripmateRepository;
    private final TripmateService tripmateService;
    private final LoginService loginService;


    @Bean
//    Authorisation
//    Setting up the Spring Security Configuration
//    function to specify multiple userDetails for flexibility
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider
                = new DaoAuthenticationProvider();
        provider.setUserDetailsService(loginService);//gets the user details from the database
        provider.setPasswordEncoder(new BCryptPasswordEncoder());//encrypts the password
        return provider;
    }

//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.authenticationProvider(authenticationProvider());
//    }


    @Bean
    //Authorisation
    //Setting up the Spring Security Configuration
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        RequestMatcher logoutRequestMatcher = new AntPathRequestMatcher("/logout");
        return http
                .csrf()
                .disable()
                .authorizeHttpRequests()
                .requestMatchers(
                        "/",
                        "/register/**",
                        "/error",
                        "api/v1/tripmate/**",
                        "/css/**",
                        "/images/**",
                        "/js/**",
                        "/tripmate/**")
                .permitAll()


                .requestMatchers("/tripmate/admin/**").hasAnyRole(ADMIN.name())
                .requestMatchers(GET, "/tripmate/admin/**").hasAnyAuthority(ADMIN_READ.name())
                .requestMatchers(POST, "/tripmate/admin/**").hasAnyAuthority(ADMIN_CREATE.name())
                .requestMatchers(PUT, "/tripmate/admin/**").hasAnyAuthority(ADMIN_UPDATE.name())
                .requestMatchers(DELETE, "/tripmate/admin/**").hasAnyAuthority(ADMIN_DELETE.name())

                .requestMatchers("/tripmate/users/**").hasRole(USER.name())
                .requestMatchers(GET, "/tripmate/users/**").hasAuthority(USER_READ.name())
                .requestMatchers(POST, "/tripmate/users/**").hasAuthority(USER_CREATE.name())
                .requestMatchers(PUT, "/tripmate/users/**").hasAuthority(USER_UPDATE.name())
                .requestMatchers(DELETE, "/tripmate/users/**").hasAuthority(USER_DELETE.name())


                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("email")
                .defaultSuccessUrl("/")
                .permitAll()
                .and()
                .logout()
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .logoutRequestMatcher(logoutRequestMatcher)
                .logoutSuccessUrl("/")
                .and()
                .build();
    }
    @Bean
    public UserDetailsService userDetailsService(){
        return username -> tripmateRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}

