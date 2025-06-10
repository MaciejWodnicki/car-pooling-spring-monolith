package org.pooling.configuration;


import jakarta.annotation.Resource;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.filter.HiddenHttpMethodFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfiguration {


    @Resource(name = "myAppUserDetailsService")
    private UserDetailsService userDetailsService;


    @Bean
    public PasswordEncoder passwordEncoder() {return new BCryptPasswordEncoder();}

    @Bean
    DaoAuthenticationProvider authProvider() {
        System.out.println("authProvider Called!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        CharacterEncodingFilter filter = new CharacterEncodingFilter();
        filter.setEncoding("UTF-8");
        filter.setForceEncoding(true);
        http.addFilterBefore(filter, CsrfFilter.class);

        http
                .authorizeHttpRequests((authz) -> authz
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll()
                        .requestMatchers("/").permitAll()
                        .requestMatchers("/appUsers*").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/availableRides/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                        .requestMatchers(HttpMethod.PUT,"/availableRides/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                        .requestMatchers(HttpMethod.DELETE,"/availableRides/**").hasAnyRole("ADMIN", "USER", "MANAGER")
                        .requestMatchers("/addRide*").hasAnyRole("ADMIN", "USER")
                        .requestMatchers("/appUserRest*").hasAnyRole("ADMIN", "MANAGER")
                        .requestMatchers("/rideRest*").hasAnyRole("ADMIN", "USER", "MANAGER")
                        .requestMatchers("/register.jsp").anonymous()
                        .requestMatchers(HttpMethod.POST,"/addAppUser").anonymous()
                        .requestMatchers("/main").anonymous()
                        .requestMatchers("/login").anonymous()
                        .requestMatchers("/resources/**").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .usernameParameter("login")
                        .passwordParameter("password")
                        .failureUrl("/login?error")
                        .defaultSuccessUrl("/",true) //use wisely
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl("/")
                )
                .exceptionHandling(logout -> logout
                        .accessDeniedPage("/accessDenied")
                )
                .httpBasic();
        return http.build();
    }

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        return new HiddenHttpMethodFilter();
    }

}














