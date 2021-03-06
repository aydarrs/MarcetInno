package ru.innopolis.demo.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class ShopSecurityConfig extends WebSecurityConfigurerAdapter {

    public ShopSecurityConfig() {
        super();
    }

    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .formLogin()
                .loginPage("/login.html")
                .failureUrl("/login-error.html")
                .defaultSuccessUrl("/login-success", true)
                .and()
                .logout()
                .logoutSuccessUrl("/index.html")
                .and()
                .authorizeRequests()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/customer/**").hasRole("CUSTOMER")
                .antMatchers("/seller/**").hasRole("SELLER")
                .antMatchers("/courier/**").hasAnyRole("ADMIN","SELLER","COURIER")
                .antMatchers("/order/stats").hasAnyRole("ADMIN","SELLER")
                .antMatchers("/order/courier/**").hasRole("COURIER")
                .antMatchers("/order/customer/**").hasRole("CUSTOMER")
                .antMatchers("/order/all").hasAnyRole("ADMIN","SELLER","COURIER")
                .antMatchers("/order/**").hasAnyRole("ADMIN","SELLER","COURIER","CUSTOMER")
                .antMatchers("/shared/**").hasAnyRole("ADMIN","CUSTOMER","SELLER","COURIER")
                .antMatchers("/users/update/**").hasAnyRole("ADMIN","CUSTOMER","SELLER","COURIER")
                .antMatchers("/users/**").hasAnyRole("ADMIN")
                .antMatchers("/shops/all").permitAll()
                .antMatchers("/product").permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/403.html");
    }

    @Override
    protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
        auth
                .jdbcAuthentication()
                .dataSource(dataSource)
                .passwordEncoder(passwordEncoder())
                .usersByUsernameQuery(
                        "SELECT user_name, password, true FROM user_account WHERE user_name = ?")
                .authoritiesByUsernameQuery(
                        "SELECT user_name, user_type FROM user_account WHERE user_name = ?"
                );
    }

}
