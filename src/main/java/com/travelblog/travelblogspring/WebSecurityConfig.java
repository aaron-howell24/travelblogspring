package com.travelblog.travelblogspring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

//	@Bean
//	public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
//	    return httpSecurity
//	        .authorizeHttpRequests(auth -> auth
//	            .requestMatchers("/admin/**").hasRole("PROTECTED")
////	            .requestMatchers("/api/**").hasRole("PROTECTED")
//	            .anyRequest().permitAll()
////	            .anyRequest().authenticated()
////	            .requestMatchers("/api/**").authenticated()
//	        )
////	        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
////	        .oauth2ResourceServer((oauth2) -> oauth2.jwt(Customizer.withDefaults()))
//			.httpBasic(Customizer.withDefaults())
//			.formLogin(Customizer.withDefaults())
//	    .build();
//	}

@Bean
    @Order(1)                                                        
    public SecurityFilterChain apiFilterChain(HttpSecurity http) throws Exception {
        http
        .cors(AbstractHttpConfigurer::disable)
        .csrf(AbstractHttpConfigurer::disable)
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .anyRequest().permitAll()
            )
            .httpBasic(Customizer.withDefaults());
        return http.build();
    }
//
    @Bean   
    @Order(2)
    public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> auth
	            .requestMatchers("/admin/**").hasRole("PROTECTED")
	            .anyRequest().permitAll()            )
            .formLogin(Customizer.withDefaults());
        return http.build();
    }
//	
//  @Bean                                                            
//  public SecurityFilterChain formLoginFilterChain(HttpSecurity http) throws Exception {
//      http
//      	.cors(AbstractHttpConfigurer::disable)
//    	.csrf(AbstractHttpConfigurer::disable)
//      .authorizeHttpRequests(auth -> auth
//	            .anyRequest().permitAll())
//          .formLogin(Customizer.withDefaults());
//      return http.build();
//  }
//	
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("PROTECTED","ADMIN")
				.build();
		return new InMemoryUserDetailsManager(user);
	}
	
	
	
	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
	

}