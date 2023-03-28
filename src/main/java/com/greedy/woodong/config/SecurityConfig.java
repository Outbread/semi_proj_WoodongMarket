package com.greedy.woodong.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.greedy.woodong.member.service.MemberService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	private MemberService memberService;
	
	@Autowired
	public SecurityConfig(MemberService memberService) {
		this.memberService = memberService;
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring()
						.antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
	}
	
	@Bean
	   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	      
	      http.csrf().disable()
	            .authorizeRequests()																						
	    		.antMatchers(HttpMethod.POST, "/admin/**").hasRole("ADMIN")
				.antMatchers("/admin/**").hasRole("ADMIN")
		    	.anyRequest().permitAll()
	         
	         /* 로그인 페이지 및 로그인 성공 후 포워딩 경로 설정 */
	         .and()
	            .formLogin()
	            .loginPage("/member/login")
	            .successForwardUrl("/")
	         .and()
	            .logout()
	            .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout")) 
	            .deleteCookies("JSESSIONID")   
	            .invalidateHttpSession(true)
	            .logoutSuccessUrl("/")

	         .and()                        
	            .exceptionHandling()               
	            .accessDeniedPage("/common/denied");   
	      
	         http.authenticationProvider(authenticationProvider());
	         
	      return http.build();
	   }
	
	@Bean
	public UserDetailsService userDetailsService() {
		return memberService;	
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
}
