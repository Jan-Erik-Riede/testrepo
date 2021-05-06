package com.example.surfshop.config;

import com.example.surfshop.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;


/**
 * Created by IntelliJ IDEA.
 * User: Tim Lueneburg
 * Date: 30.04.2021
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new CustomUserDetailsService();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }

    /**
     * Hier ist die Login security konfiguriert. Der erste antMathers() gibt an welche ressourcen zugelassen
     * werden damit auch css dateien usw. geladen werden. Danach wird festgelegt das jeder auf die Login-page darf.
     * Dann haben wir noch eine eigene Login-page angegeben und eine succesURl mit true damit wir den redirect bekommen
     * sowie failureURL.
     * Beim Logout invalidaten wir die Session clearen die Authentikation und löschen die Cookies.
     * Wegen diesem Logout Mapping brauchen wir einen Logout Controller der uns das Attribut Erfolgreich Abgemeldet setzt
     * für die Login Seite
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/css/**", "/images/**, /js/**", "/fonts/**").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/benutzeruebersicht", "/createuser").access("hasRole('CHEF') or hasAuthority('CHEF')")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/access_denied")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/login-user", true)
                .failureUrl("/login-error")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/logout-success")
                .invalidateHttpSession(true)
                .clearAuthentication(true)
                .and()
                .sessionManagement()
                .maximumSessions(1)
                .maxSessionsPreventsLogin(true)
                .sessionRegistry(sessionRegistry());
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    /**
     * Funktion damit ein Benutzer der sich abmeldet auch trotz maximaler Session wieder anmelden kann.
     * Quasi Tracker der checkt ob der Benutzer sich weider anmeldenm kann.
     * @return
     */
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }
}
