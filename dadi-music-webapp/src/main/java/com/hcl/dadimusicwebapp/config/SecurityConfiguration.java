package com.hcl.dadimusicwebapp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
  @Autowired
  private UserDetailsService userDetailsService;

  @Bean
  public AuthenticationProvider authProvider() {
    DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
    provider.setUserDetailsService(userDetailsService);
    provider.setPasswordEncoder(new BCryptPasswordEncoder());
    return provider;
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.
       csrf().disable().authorizeRequests()
      .antMatchers("/", "/search**", "/cart", "/register", "/add/**", "/delete/**","/images/**","/register","/checkout").permitAll()
      .antMatchers("/admin/**","/add-genre","/add-album","/add-artist","/add-song").hasRole("ADMIN")
      .anyRequest().authenticated()
      .and()
      .formLogin()
      .loginPage("/login")
      .permitAll()
      .and()
      .logout().logoutUrl("/logout").logoutSuccessUrl("/").invalidateHttpSession(true)
      .clearAuthentication(true);

    /*
     * .loginProcessingUrl("/welcome") //change as per login form mappin
     * .defaultSuccessUrl("/welcome") //page after successfull login..//can redirect
     * to custom page if role= admin from controller. .and()
     * .logout().logoutSuccessUrl("/").invalidateHttpSession(true)
     * .clearAuthentication(true) .and()
     * .exceptionHandling().accessDeniedPage("/accessdenied");
     */
  }

  @Bean
  public BCryptPasswordEncoder encoder() {
    return new BCryptPasswordEncoder();
  }

}