package kg.itacademy.stomservice.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT t.login, t.password, t.is_active FROM user_stom_service t WHERE t.login = ?")
                .authoritiesByUsernameQuery(
                        "SELECT u.login, r.name_role " +
                                "FROM stom_users_roles sur " +
                                "INNER JOIN user_stom_service u " +
                                "   on sur.user_id = u.id " +
                                "INNER JOIN stom_service_roles r " +
                                "   on sur.role_id = r.id " +
                                "WHERE u.login = ? AND u.is_active = true"
                );
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .csrf().disable()
                .cors().disable()
                .authorizeRequests()

//                .antMatchers(HttpMethod.POST, "/api/user/*").permitAll()


                .antMatchers(HttpMethod.GET, "/api/dentist/**").hasAnyRole("Admin", "User") // можно hasRole("Admin") -- тогда
//                // доступ к справочнику только у Admin, hasAnyRole("Admin", "User") -- если у нас несколько ролей
                .antMatchers(HttpMethod.POST, "/api/dentist/*").hasRole("Admin")
                .antMatchers(HttpMethod.PUT, "/api/dentist/*").hasRole("Admin")
                .antMatchers(HttpMethod.DELETE, "/api/dentist/*").hasRole("Admin")

                //.antMatchers(HttpMethod.GET, "/api/patient/*").hasRole("Admin") //permitAll()-- можно все
                //.antMatchers(HttpMethod.POST, "/api/patient/*").hasRole("Admin")
                //.antMatchers(HttpMethod.PUT, "/api/patient/*").hasRole("Admin")
                //.antMatchers(HttpMethod.DELETE, "/api/patient/*").hasRole("Admin")

                .antMatchers("/api/patient/*").hasRole("Admin") //это заменяет строки 57-60


                //.antMatchers(HttpMethod.GET, "/api/dentistSchedule/*").hasRole("Admin") //permitAll()-- можно все
                //.antMatchers(HttpMethod.POST, "/api/dentistSchedule/*").hasRole("Admin")
                //.antMatchers(HttpMethod.PUT, "/api/dentistSchedule/*").hasRole("Admin")
                //.antMatchers(HttpMethod.DELETE, "/api/dentistSchedule/*").hasRole("Admin")

                .antMatchers("/api/dentistSchedule/*").hasRole("Admin") //это заменяет строки 65-68

                //.antMatchers(HttpMethod.GET, "/api/dentistAppointment/*").permitAll()
                //.antMatchers(HttpMethod.POST, "/api/dentistAppointment/*").permitAll()
                //.antMatchers(HttpMethod.PUT, "/api/dentistAppointment/*").permitAll()
                //.antMatchers(HttpMethod.DELETE, "/api/dentistAppointment/*").permitAll()

                .antMatchers("/api/dentistAppointment/*").permitAll() //это заменяет строки 72-75

                .antMatchers("/api/role/*").permitAll()

                .anyRequest().permitAll()

                .and()// означает соединение
                .httpBasic(); // означает тип авторизации
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}