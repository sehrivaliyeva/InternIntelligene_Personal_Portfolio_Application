package portfolio.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import portfolio.service.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable)  // CSRF qorumasını deaktiv edir (JWT ilə sessiyasız autentifikasiya üçün)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html")  // Swagger UI və API sənədlərinə hər kəsin girməsinə icazə verir
                        .permitAll()
                        .requestMatchers("/user-register", "/user-login")  // Qeydiyyat və login səhifələrinə hər kəsin girməsinə icazə verir
                        .permitAll()
                        .requestMatchers("/v1/portfolio/**")  // Yalnız 'USER' rolu olan istifadəçilərə portfelə daxil olmağa icazə verir
                        .hasRole("USER")
                        .anyRequest()  // Qalan bütün sorğular doğrulama tələb edir
                        .authenticated()
                )
                .httpBasic(AbstractHttpConfigurer::disable)  // Basic Authentication deaktiv edilir (JWT istifadə olunur)
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Sessiya yaratmaq yerinə stateless metod istifadə edilir (sessiyasız)
                // Anonim istifadəçilərə də icazə verilir (Swagger və qeydiyyat üçün)
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)  // JWT filtrini əlavə edir
                .build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Burada 'allowedOrigins' düzgün təyin edilir
                registry.addMapping("/**").allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")  // Hər hansı bir HTTP metoduna icazə verir
                        .allowedHeaders("*")  // İstənilən başlıqla sorğulara icazə verir
                        .allowCredentials(true);  // Cookies və authorization başlıqlarını göndərməyə icazə verir
            }
        };
    }
}
