package org.ylzl.eden.demo.app.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.ylzl.eden.demo.app.user.filter.JwtAuthenticationFilter;
import org.ylzl.eden.demo.app.user.handler.*;
import org.ylzl.eden.demo.app.user.service.UserDetailService;

/**
 * @author hollis
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private final LoginFailureHandler loginFailureHandler;

	private final LoginSuccessHandler loginSuccessHandler;

	private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

	private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

	private final UserDetailService userDetailService;

	private final JWTLogoutSuccessHandler jwtLogoutSuccessHandler;

    @Bean
	JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
		return new JwtAuthenticationFilter(authenticationManager());
    }


    private static final String[] URL_WHITELIST = new String[]{
		"/login",
		"/logout",
		"/captcha",
		"/favicon.ico"
	};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
                // 登录配置
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .logout()
                .logoutSuccessHandler(jwtLogoutSuccessHandler)
                // 禁用session
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                // 配置拦截规则
                .and()
                .authorizeRequests()
                .antMatchers(URL_WHITELIST).permitAll()
				.antMatchers("/profile/**").anonymous()
				.antMatchers("/common/download**").anonymous()
				.antMatchers("/common/download/resource**").anonymous()
				.antMatchers("/swagger-ui.html").anonymous()
				.antMatchers("/swagger-resources/**").anonymous()
				.antMatchers("/webjars/**").anonymous()
				.antMatchers("/*/api-docs").anonymous()
				.antMatchers("/druid/**").anonymous()
				.antMatchers("/**/users/login").anonymous()
				.antMatchers("/**/users/create").anonymous()
                .anyRequest().authenticated()
                // 异常处理器
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                .accessDeniedHandler(jwtAccessDeniedHandler)
                // 配置自定义的过滤器
                .and()
                .addFilter(jwtAuthenticationFilter())
                ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }
}
