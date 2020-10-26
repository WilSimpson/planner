package me.wildev.planner.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter
{
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthTokenFilter.class);

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtAuthEntryPoint jwtAuthEntryPoint;

    @Override
    public void configure(AuthenticationManagerBuilder builder) throws Exception
    {
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
        logger.info("Decoded message: {}", passwordEncoder().encode("wil"));
    }

    // Used when sending passwords
    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    // Used so we used the same authentication manager with the web security config
    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception
    {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception
    {
        http.cors().and()
                // Dont need since we're authorized
                .csrf().disable()

                // Set the exception handling to the entry point which will be logged and responded to
                .exceptionHandling().authenticationEntryPoint(jwtAuthEntryPoint).and()

                // Using JWT so we can be stateless
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()

                // Force authorization for all api requests
                .authorizeRequests().antMatchers("/api/**").authenticated().and()

                // Setup filter for authentication
                .addFilterBefore(new JwtAuthTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }



}
