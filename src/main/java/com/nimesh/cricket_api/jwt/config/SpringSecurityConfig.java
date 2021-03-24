package com.nimesh.cricket_api.jwt.config;

import com.nimesh.cricket_api.jwt.service.CustomUserDetailsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	CustomUserDetailsService userDetailsService;

	@Autowired
	private CustomJwtAuthenticationFilter customJwtAuthenticationFilter;

	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		// We don't need CSRF for this example
		httpSecurity.csrf().disable()
		.antMatcher("/cricket/api/**").authorizeRequests().antMatchers(HttpMethod.POST).hasRole("ADMIN").and().
				// dont authenticate this particular request
				authorizeRequests().antMatchers("/authenticate", "/register").permitAll().and()
				
				.authorizeRequests().anyRequest().authenticated().and().exceptionHandling()
				.authenticationEntryPoint(unauthorizedHandler).and().
				// make sure we use stateless session; session won't be used to
				// store user's state.
				sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		
		// Add a filter to validate the tokens with every request
		httpSecurity.addFilterBefore(customJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}
