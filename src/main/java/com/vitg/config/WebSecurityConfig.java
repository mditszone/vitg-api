package com.vitg.config;

import com.vitg.jwt.JwtAuthEntryPoint;
import com.vitg.jwt.JwtAuthTokenFilter;
import com.vitg.serviceimpl.UserDetailsServiceImpl;
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
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;


    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {

        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();

    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/slider/**").permitAll()
                .antMatchers("/api/faq/**").permitAll()
                .antMatchers("/api/course/**").permitAll()
                .antMatchers("/api/subCourse/**").permitAll()
                .antMatchers("/api/topic/**").permitAll()
                .antMatchers("/api/subTopic/**").permitAll()
                .antMatchers("/api/subTopicConcept/**").permitAll()
                .antMatchers("/api/studentSubCourse/**").permitAll()
                .antMatchers("/api/faculty/getAllFaculty/**").permitAll()
                .antMatchers("/api/faculty/facultyLogin/**").permitAll()
                .antMatchers("/api/faculty/facultyLogin/verifyOtp/**").permitAll()
                .antMatchers("/api/route53/**").permitAll()
                .antMatchers("/api/fbIntegration/**").permitAll()
                .antMatchers("/api/batch/**").permitAll()
                .antMatchers("/api/student/editStudent").permitAll()
                .antMatchers("/js/**", "/css/**", "/img/**", "/webjars/**", "/html/**", "/images/**",
                        "/swagger-resources/**", "/swagger-resource/**")
                .permitAll().antMatchers("/v2/api-docs", "/configuration/**", "/swagger*/**", "/webjars/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
