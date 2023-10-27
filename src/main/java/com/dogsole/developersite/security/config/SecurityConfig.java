package com.dogsole.developersite.security.config;

import com.dogsole.developersite.account.service.UserInfoUserDetailsService;
import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
//    @Autowired
//    private JwtAuthFilter authFilter;

    @Bean
    //authentication
    //사용자별 데이터를 로드하며 주로 인증목적으로 쓰임  (UserInfoUserDetailsService의 인스턴스반환)
    public UserDetailsService userDetailsService() {
       return new UserInfoUserDetailsService();
    }

    //HttpSecurity 를 사용해 보안필터체인 구성을 정의
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().disable() //CSRF(Cross-Site Request Forgery)
                .authorizeHttpRequests(request -> request
                        .dispatcherTypeMatchers(DispatcherType.FORWARD).permitAll() //아래는 인증없이 허용되는 URL지정
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/images/**"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/h2-console/**"),
                                new AntPathRequestMatcher("/checkToken"),
                                new AntPathRequestMatcher("/tokenCreate/**"),
                                new AntPathRequestMatcher("/checkToken/**")
                        ).permitAll()
                        .anyRequest().authenticated() //위에 지정한 url패턴과 일치 하지않는 모든 요청에 인증을 요구한다.
                )
                .formLogin(login -> login //폼기반 인증 로그인 제공
//                        .loginPage("/account/loginpage")
//                        .loginProcessingUrl("/login")
//                        .usernameParameter("userEmail")
//                        .passwordParameter("passwd")
                        .defaultSuccessUrl("/", true) //로그인에 성공한 경우 사용자는 항상 "/"경로로 리디렉션됨
                        .permitAll()
                )
                .logout(withDefaults());
                // '/logout'이라는 url로 POST 요청을 보내면 로그아웃 처리됨.
                //스프링시큐리티는 로그아웃 요청 시 CSRF 토큰을 요구함, 보안 목적으로 사용되며 요청 실행 시 CSRF토큰을 함께 제공해야함
        // 로그아웃 버튼     <form th:action="@{/logout}" method="post">
        //                  <input type="submit" value="로그아웃">
        //                 </form>

        return http.build(); //HttpSecurity 객체를 사용하여 보안필터체인을 정의 및 구성
                            //http.build() 호출하여 구성 후 SecurityFilterChain을 반환하면 이 구성이 애플리케이션에 적용됨
    }

    //스프링시큐리티에서 사용자의 비밀번호를 안전히 저장하고 비교하기위해 사용되는 PasswordEncoder빈을 구성
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); //BCryptPasswordEncoder 클래스의 인스턴스를 반환
    }                                       //BCryptPasswordEncoder는 스프링시큐리티에서 널리 사용되는 비번 해시함수
                                            //사용예제
                                            // PasswordEncoder passwordEncoder = passwordEncoder();
                                            // String hashedPassword = passwordEncoder.encode(rawPassword);
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider(); //사용자의 인증을 처리하는 인터페이스.
        authenticationProvider.setUserDetailsService(userDetailsService()); //authenticationProvider에 사용자 정보를 가져올 userDetailsService()넣기
        authenticationProvider.setPasswordEncoder(passwordEncoder());//authenticationProvider에 사용자 비밀번호를 비교할때 사용할 암호화 설정
        return authenticationProvider;
    }
    @Bean //사용자 인증 관리를 위한 AuthenticationManager 생성
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); //사용자 인증을 관리.(사용자 자격 증명)
    }

}
