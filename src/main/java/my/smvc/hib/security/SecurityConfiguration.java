package my.smvc.hib.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{
	@Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.inMemoryAuthentication()
            .passwordEncoder(NoOpPasswordEncoder.getInstance())
            .withUser(User.withUsername("ADMIN").password("PassAdmin").roles("ADMIN"))
        	.withUser(User.withUsername("USER").password("PassUser").roles("USER"));
            
            //ORIGINAL
//        		.withUser("admin").password("admin")
//                .roles("USER", "ADMIN");
        
        
    }
	
	
	/**
	 * MOST RESTRICTIVE TO LEAST RESTRICTIVE
	 */
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		
		http.authorizeRequests()
		.antMatchers("/delete-student", "/add-student").hasRole("ADMIN")  // ONLY ADMIN CAN ADD OR DELETE STUDENT
		.antMatchers("/list-students", "/update-student", "/save-student").hasAnyRole("ADMIN", "USER") // USER (USED BY STUDENTS CAN VIEW AND UPDATE AND SAVE)
		.antMatchers("/").permitAll().and().formLogin();
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
		
		
		//ORIGINAL
//        http.authorizeRequests().antMatchers("/login", "/h2-console/**").permitAll()
//                .antMatchers("/", "/*student*/**").access("hasRole('USER')").and()
//                .formLogin();
//        
//        http.csrf().disable();
//        http.headers().frameOptions().disable();
    }
}



//@Override
//protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//	auth.jdbcAuthentication().dataSource(dataSource).passwordEncoder(getPasswordEncoder());
//	
//	//FOR MYSQL VIA SCHEMA.SQL AND DATA.SQL
////			.withUser(User.withUsername("ADMIN").password("PassAdmin").roles("ADMIN"))
////			.withUser(User.withUsername("USER").password("PassUser").roles("USER"));
//}
//
//@Bean
//public PasswordEncoder getPasswordEncoder() {
//	return NoOpPasswordEncoder.getInstance();
//}
//
//// MOST RESTRICTIVE TO LEAST RESTRICTIVE
//// Add Record and Delete Record for ADMIN only
//// View All Records and Update Record for both ADMIN AND USER ROLES
//@Override
//protected void configure(HttpSecurity http) throws Exception {
//
//	// Latest version of Spring Security has CSRF enabled by default blocking POST
//	// requests
//	http.csrf().disable().authorizeRequests().antMatchers("/students/showForm", "/students/delete").hasRole("ADMIN")
//			.antMatchers("/students/saveStudent", "/students/updateForm", "/students/list")
//			.hasAnyRole("ADMIN", "USER").antMatchers("/").permitAll().and().formLogin();
//
//}
//
////@Override
////public void configure(WebSecurity web) throws Exception {
////	web.ignoring().antMatchers("/h2-coonsole/**");
////}