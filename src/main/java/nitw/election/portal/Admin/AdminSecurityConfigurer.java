//package nitw.election.portal.Admin;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.http.HttpMethod;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//import java.net.Authenticator;
//
//@EnableWebSecurity
//public class AdminSecurityConfigurer extends WebSecurityConfigurerAdapter {
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
//        // set configurations
//        auth.inMemoryAuthentication()
//                .withUser("reddymam")
//                .password("reddymam")
//                .roles("admin")
//                .and()
//                .withUser("sushith")
//                .password("sushith")
//                .roles("user");
//    }
//
//    @Bean
//    public PasswordEncoder getPasswordEncoder(){
//        return NoOpPasswordEncoder.getInstance();
//    }
//
//    @Override
//    protected void configure(HttpSecurity http) throws Exception{
//        http.authorizeHttpRequests()
//                .antMatchers(HttpMethod.POST,"/student/**").permitAll()
//                .antMatchers("/**").permitAll()//hasRole("admin")
////                .antMatchers("/**").permitAll()//hasAnyRole("user","admin")
//                        .and().formLogin();
////        http.authorizeHttpRequests()
////                .antMatchers("/desis").hasAnyRole("user","admin")
////                .and().formLogin();
//    }
//}
