package com.example.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private DataSource datasource;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		/*
		 * auth.inMemoryAuthentication()
		 * .withUser("wilki").password(passwordEncoder().encode("wilki")).roles(
		 * "MEMBRE") .and()
		 * .withUser("user2").password(passwordEncoder().encode("user2Pass")).roles(
		 * "USER") .and()
		 * .withUser("admin").password(passwordEncoder().encode("adminPass")).roles(
		 * "ADMIN");
		 */

		auth.jdbcAuthentication().dataSource(datasource).usersByUsernameQuery(
				"SELECT username as principal,password as credentials, active FROM utilisateur WHERE username=?")
				.authoritiesByUsernameQuery(
						"SELECT user_id as principal ,role_id as role FROM user_roles WHERE user_id=?")
				.rolePrefix("ROLE_").passwordEncoder(passwordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		  http
		  .csrf()
		  .disable()
          .formLogin()
		  .loginPage("/soft_church/login")
		  .defaultSuccessUrl("/soft_church/home",true);
		
		   http.logout()
		  .logoutSuccessUrl("/soft_church/login")
		  .logoutUrl("/soft_church/logout")
		  .permitAll();

		http.authorizeRequests()
		. antMatchers("/soft_church/home").hasAnyRole("MEMBRE","ADMIN","SEC")
		.antMatchers("/soft_church/formAttribuerRole").hasRole("ADMIN")
		.antMatchers("/soft_church/attribuerRole").hasRole("ADMIN")
		.antMatchers("/soft_church/listerUsers").hasRole("ADMIN")
		.antMatchers("/soft_church/desactivUser").hasRole("ADMIN")
		.antMatchers("/soft_church/reactivUser").hasRole("ADMIN")
	    .antMatchers("/soft_church/getMembre").hasAnyRole("MEMBRE","SEC","ADMIN")
	    .antMatchers("/soft_church/modifierMembre").hasAnyRole("ADMIN","SEC")
	    //.antMatchers("/soft_church/resetPassword").hasAnyRole("MEMBRE","SEC","")

		.antMatchers("/soft_church/changePassword").hasAnyRole("MEMBRE","ADMIN","SEC")

		.antMatchers("/soft_church/formChangePassword").hasAnyRole("MEMBRE","ADMIN","SEC")

		.antMatchers("/soft_church/formsaveMembre").hasAnyRole("ADMIN","SEC")

		.antMatchers("/soft_church/formsearchMembre").hasAnyRole("ADMIN","SEC")

		.antMatchers("/soft_church/formlisterMembre").hasAnyRole("ADMIN","SEC")

		.antMatchers("/soft_church/updateMembre").hasAnyRole("ADMIN","SEC")

		.antMatchers("/soft_church/rechercherMembre").hasAnyRole("ADMIN","SEC")

		.antMatchers("/soft_church/editerMembre").hasAnyRole("ADMIN","SEC")

		.antMatchers("/soft_church/getPhoto").hasAnyRole("ADMIN","SEC")

		.antMatchers("/soft_church/getImage").hasAnyRole("MEMBRE","SEC","ADMIN")
		
		.antMatchers("/soft_church/profileMembre").hasAnyRole("MEMBRE","SEC","ADMIN")
		
		
		.antMatchers("/soft_church/formSaveCulte").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/ajouterMembre").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/lstmembres").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/retirerMembre").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/ajouterMembre").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/saveCulte").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/lister_cultes").hasAnyRole("MEMBRE","SEC","ADMIN")
		.antMatchers("/soft_church/presence_au_culte").hasAnyRole("MEMBRE","SEC","ADMIN")
		
		.antMatchers("/soft_church/dashboard").hasAnyRole("ADMIN")
		
		
		.antMatchers("/soft_church/form_don").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/save_don_nature").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/save_don_espece").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/lister_don_especes").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/lister_don_natures").hasAnyRole("SEC","ADMIN")
		
		
		
		.antMatchers("/soft_church/formSaveDime").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/listerDimesCashes").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/listerDimesCheques").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/saveDimeCheque").hasAnyRole("SEC","ADMIN")
		.antMatchers("/soft_church/saveDimeCash").hasAnyRole("SEC","ADMIN");
		
		/*
		 * .and() .exceptionHandling().accessDeniedPage("/soft_church/login");
		 */

		/*
		 * http.logout() .logoutUrl("/soft_church/login") .invalidateHttpSession(true)
		 * .deleteCookies("JSESSIONID");
		 */
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web
		.ignoring()
		.antMatchers("/assets/**")
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/css/**")
				.antMatchers("/js/**")
				.antMatchers("/lib/**")
				.antMatchers("/lib/js/**")
				.antMatchers("/image/**");
	}

	/*
	 * auth.inMemoryAuthentication().withUser("wilki") .password("1234")
	 * .roles("MEMBRE","ADMIN");
	 */

	// }

	/*
	 * http .authorizeRequests()
	 * 
	 * .anyRequest() .authenticated() .and() .formLogin()
	 * .defaultSuccessUrl("/soft_church/saveMembre", true)
	 * .loginPage("/soft_church/login") .permitAll() .and() .logout() .permitAll();
	 */

	/*
	 * @Override protected void configure(final HttpSecurity http) throws Exception
	 * {
	 * 
	 * http .csrf().disable() .authorizeRequests()
	 * .antMatchers("/saveMembre").hasRole("ADMIN") //
	 * .antMatchers("/anonymous*").anonymous()
	 * .antMatchers("/soft_church/login").permitAll() .anyRequest().authenticated()
	 * .and() .formLogin() .loginPage("/soft_church/login")
	 * .loginProcessingUrl("/perform_login") .defaultSuccessUrl("/saveMembre",
	 * true);
	 */
	// .failureUrl("/login.html?error=true")
	// .failureHandler(authenticationFailureHandler()) .and() .logout()
	// .logoutUrl("/perform_logout") .deleteCookies("JSESSIONID");

	// .logoutSuccessHandler(logoutSuccessHandler());
	/*
	 * http .authorizeRequests() .antMatchers( "/resources/**" ).permitAll(); http
	 * .authorizeRequests()
	 * 
	 * .anyRequest() .authenticated() .and() .formLogin()
	 * .loginPage("/soft_church/login") .permitAll() .and() .logout() .permitAll();
	 * }
	 */
}
