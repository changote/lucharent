package com.example.rent.config;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.example.rent.auth.CustomAuthenticationProvider;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Value("${front.mobile.pattern}")
    private String mobilePattern;

    @Value("${front.web.pattern}")
    private String webPattern;

    String[] resources = new String[]{
            "/assets/**", "/css/**", "/static/img/**", "/js/**"
    };

    @Autowired
    private CustomAuthenticationProvider authenticationManager;

    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return authenticationManager;
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList(mobilePattern, webPattern));
        corsConfiguration.setAllowedHeaders(new ArrayList<String>(Arrays.asList("*")));
        corsConfiguration.setAllowedMethods(new ArrayList<String>(Arrays.asList("GET", "POST", "PUT", "DELETE", "PUT", "PATCH", "DELETE")));
        corsConfiguration.setAllowCredentials(true);

        httpSecurity
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(resources).permitAll()
                .antMatchers("/login", "/login/**", "/**/swagger-ui/**", "/status","/propertysbycity","/propertysforhome").permitAll()

                .and().authorizeRequests().antMatchers("/mi_cuenta").authenticated()
                .and().authorizeRequests().antMatchers("/notifications").authenticated()

                .anyRequest()
                .authenticated()
                .and().csrf().disable().cors().configurationSource(request -> corsConfiguration).and()

                .formLogin()
                .loginPage("/").permitAll()
                .and()


                .logout()
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .logoutSuccessUrl("/");
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(Arrays.asList(mobilePattern, webPattern));
        corsConfiguration.setAllowedHeaders(new ArrayList<String>(Arrays.asList("*")));
        corsConfiguration.setAllowedMethods(new ArrayList<String>(Arrays.asList("GET", "POST", "PUT", "DELETE", "PUT", "PATCH", "DELETE")));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}