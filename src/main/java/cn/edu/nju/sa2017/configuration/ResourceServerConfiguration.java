package cn.edu.nju.sa2017.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

/**
 * Created by njucjc on 2017/6/17.
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfiguration extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/students/pageInfo").permitAll()
           //     .antMatchers("/batchimport").permitAll()
                .antMatchers(HttpMethod.POST, "/students/*").permitAll()
                .antMatchers(HttpMethod.GET, "/students").permitAll()
                .antMatchers(HttpMethod.POST,"/students/add").permitAll()
                .antMatchers(HttpMethod.POST,"/students/update").permitAll()
                .antMatchers(HttpMethod.POST, "/students/delete").permitAll()
                .antMatchers(HttpMethod.GET, "/students/find").permitAll()
                .anyRequest().authenticated();
    }

}
