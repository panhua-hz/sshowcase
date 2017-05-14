package config;

import java.util.Arrays;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.encoding.LdapShaPasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;

import services.AuthUserService;

@Configuration
@EnableWebSecurity
public class ConfigCtxSecurity extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource dataSource; 
	
	@Bean
	public DefaultSpringSecurityContextSource contextSource() {
		//return  new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:8389/"), "dc=springframework,dc=org");
		return  new DefaultSpringSecurityContextSource(Arrays.asList("ldap://localhost:10389"), "dc=springframework,dc=org");
	}

	protected void memoryAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
			.withUser("andrew").password("aaa").roles("ADMIN","EMPLOYEE")
			.and()
			.withUser("m001").password("111").roles("MANAGER")
			.and()
			.withUser("guest").password("ggg").roles("GUEST");
	}
	
	protected void dataSourceAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.jdbcAuthentication().dataSource(dataSource)
		.usersByUsernameQuery("select username, password, 'true' from user where username=?")
		.authoritiesByUsernameQuery("select username, 'ROLE_USER' from user where username=?");
	}
	
	protected void userServiceAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(new AuthUserService());
	}
	
	protected void ldapAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth.ldapAuthentication()
		.userDnPatterns("uid={0},ou=people")
		.groupSearchBase("ou=groups")
		.contextSource(contextSource())
		.passwordCompare()
		.passwordEncoder(new LdapShaPasswordEncoder())
		.passwordAttribute("userPassword");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		memoryAuth(auth);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		securityFilterDefault(http);
	}
	
	protected void securityFilterDefault(HttpSecurity http) throws Exception{
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().and().httpBasic();
	}
	protected void securityFilterPage(HttpSecurity http, boolean isRememberMe) throws Exception{
		
		http
        .authorizeRequests()
            .antMatchers("/", "/home").permitAll()
            .anyRequest().authenticated()
            .and()
        .formLogin()
            .loginPage("/login")
            .permitAll()
            .and()
            //.rememberMe().tokenValiditySeconds(1440).key("mysay").and()  //remember could only used by userDetailsService
        .httpBasic()
        	.and()
        .logout()
            .permitAll();
	}
	
}
