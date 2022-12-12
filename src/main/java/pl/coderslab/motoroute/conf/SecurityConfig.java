package pl.coderslab.motoroute.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig {
    @Bean
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/",
                        "/register",
                        "/login",
                        "/about",
                        "/routes",
                        "/contact").permitAll()
                .antMatchers("/app/**").hasRole("USER")
                .antMatchers("/admin/**").hasRole("ADMIN")
                .and().formLogin().defaultSuccessUrl("/app/route/dashboard")
                .and().logout().logoutSuccessUrl("/");
        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
