package com.bank.cc.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


@Configuration
@EnableAuthorizationServer
@EnableResourceServer
public class OAuth2CustomResourceServerConfiguration extends ResourceServerConfigurerAdapter {


  @Value("${creditcard.enableoauth2}")
  private boolean isSecurityEnabled;

  @Override
  public void configure(HttpSecurity http) throws Exception {
    if (!isSecurityEnabled) {
      http.authorizeRequests().antMatchers("/creditcard/**").authenticated().antMatchers("/")
          .permitAll();
    } else {
      http.authorizeRequests().antMatchers("/").permitAll();
    }
  }

//  @Override
//  public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
//    resources.authenticationEntryPoint(new CustomOAuth2AuthenticationEntryPoint());
//  }
}
