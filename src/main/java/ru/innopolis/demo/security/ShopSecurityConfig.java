package ru.innopolis.demo.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ShopSecurityConfig extends WebSecurityConfigurerAdapter {

    public ShopSecurityConfig() {
        super();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login-error.html")
                .and()
                .logout()
                .logoutSuccessUrl("/index.html")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/customer/**").hasRole("CUSTOMER")
                .antMatchers("/seller/**").hasRole("SELLER")
                .antMatchers("/courier/**").hasRole("COURIER")
                .antMatchers("/shared/**").hasAnyRole("ADMIN","CUSTOMER","SELLER","COURIER")
                .antMatchers("/users/**").hasAnyRole("ADMIN")
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403.html");

    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("admin").password("{noop}admin").roles("ADMIN").and()
                .withUser("customer").password("{noop}customer").roles("CUSTOMER").and()
                .withUser("seller").password("{noop}seller").roles("SELLER").and()
                .withUser("courier").password("{noop}courier").roles("COURIER").and()
                .withUser("supervisor").password("{noop}root").roles("ADMIN","CUSTOMER","SELLER","COURIER");
    }

}
