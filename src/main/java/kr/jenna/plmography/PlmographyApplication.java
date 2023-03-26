package kr.jenna.plmography;

import kr.jenna.plmography.interceptors.AuthenticationInterceptor;
import kr.jenna.plmography.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class PlmographyApplication {
    @Value("${jwt.secret}")
    private String secret;

    public static void main(String[] args) {
        SpringApplication.run(PlmographyApplication.class, args);
    }

    @Bean
    public WebSecurityCustomizer ignoringCustomizer() {
        return (web) -> web.ignoring().requestMatchers("/**");
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Autowired
            private HandlerInterceptor backdoorControlInterceptor;

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("*").allowedMethods("*");
            }

            @Override
            public void addInterceptors(InterceptorRegistry registry) {
                registry.addInterceptor(authenticationInterceptor());
                registry.addInterceptor(backdoorControlInterceptor);
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new Argon2PasswordEncoder(16, 32, 1, 1 << 14, 2);
    }

    @Bean
    public JwtUtil jwtUtil() {
        return new JwtUtil(secret);
    }

    @Bean
    public HandlerInterceptor authenticationInterceptor() {
        return new AuthenticationInterceptor(jwtUtil());
    }
}
