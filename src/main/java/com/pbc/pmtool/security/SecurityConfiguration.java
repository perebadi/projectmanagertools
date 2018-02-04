package com.pbc.pmtool.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("userService")
	protected UserDetailsService userService;
	
	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(userService).passwordEncoder(new BCryptPasswordEncoder());
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/static/css/*","/css/*","/css/codemirror/*","/css/*/*" ,"/fonts/*","/js/plugins/*","/js/*","/sass/*","/tables/*","/assets/*","/img/*","/audio/*","/css/bootstrap/*","/css/fontawesonme/*","/css/summernote/*","/css/jquery/*","/css/blueimp/*","/css/codemirror/*","/css/dropzone/*","/css/nvd3/*","/css/rickshaw/*").permitAll()
			.anyRequest().authenticated()
			.and()
			.formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
			.usernameParameter("username").passwordParameter("password")
			.defaultSuccessUrl("/loginsuccess").permitAll()
			.and()
			.logout().logoutUrl("/logout").logoutSuccessUrl("/login?logout")
			.permitAll();
		
	}
	
	

}