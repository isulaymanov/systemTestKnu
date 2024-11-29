package com.alien.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;

import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.core.userdetails.UserDetailsService;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.alien.security.jwt.JWTSecureEntryPoint;
import com.alien.security.jwt.JwtAuthenticationFilter;
import com.alien.security.jwt.TokenBlacklistService;

import jakarta.servlet.http.HttpServletResponse;


import java.util.List;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {
	   
	  @Autowired
	  private UserDetailsService userdetailsService;
	
	  @Autowired
      private JWTSecureEntryPoint  point;
	  
      @Autowired
      private JwtAuthenticationFilter filter;
	  
      @Autowired
      private PasswordEncoder passwordEncoder;
      
      @Autowired
      private TokenBlacklistService tokenBlacklistService;


		
	   @Bean
	    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	        http.csrf((c)-> c.disable())
			//Добавил 11.11.2024 last chang в моем коде время 21:35 если какиета api отваляться то тут надо удалить
			.cors(cors -> cors.configurationSource(corsConfigurationSource())) // Включение CORS
			//--------------------------------------------------------------------------------
        	//.cors((c) -> c.disable())  //Это нужно наоборот раскоменьтирватт время 21:35
	        .authorizeHttpRequests((authz) ->
	        authz.requestMatchers(
	        		"/api/login",
	        		"/api/user",
	        		"/api/createuser",
	        		"/api/adduser",
				    "/api/faculty/**",
					"/api/education-levels/**",
					"/api/direction/**",
					"/api/department/**",
	        		"/api/refreshToken","/assignment/**","/question/**","/quiz/**").permitAll()
	        .requestMatchers("/api/**").authenticated()
            .anyRequest().authenticated()
            )
	        .exceptionHandling(ex -> ex.authenticationEntryPoint(point))
            .sessionManagement(session -> 
            session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .logout(logout -> logout
                    .logoutUrl("/api/logout")
                    .logoutSuccessHandler((request, response, authentication) -> {
                        // Invalidate token on logout
                        String token = (String) filter.extractTokenFromRequest(request); 
                        tokenBlacklistService.addToBlacklist(token);
                        tokenBlacklistService.toString();
                        response.setStatus(HttpServletResponse.SC_OK);
                    })
                    .invalidateHttpSession(true)
                );
             http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
	        return http.build();
	    }


 
	   private Customizer<FormLoginConfigurer<HttpSecurity>> withDefaults() {
	        return Customizer.withDefaults();
	    }
	   
	   @Bean
	   public DaoAuthenticationProvider  daoAuthenticationProvider() {
		   DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
		   provider.setUserDetailsService(userdetailsService);
		   provider.setPasswordEncoder(passwordEncoder);
		   return provider;
	   }
//Добавил 11.11.2024 last chang в моем коде время 21:35 если какиета api отваляться то тут надо удалить
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(List.of("http://localhost:8080")); // Укажите разрешенные источники
		configuration.setAllowedMethods(List.of("GET", "POST", "PATCH", "DELETE", "OPTIONS","CONNECT","PUT","TRACE","HEAD"));
		configuration.setAllowedHeaders(List.of("Authorization", "Content-Type"));
		configuration.setAllowCredentials(true);

		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
	//-----------------------------------------------------------------------------------
	  
}
