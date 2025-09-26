package rayka.sos.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Desabilita o CSRF
                .csrf(csrf -> csrf.disable())
                // Define as regras de autorização
                .authorizeHttpRequests(authorize -> authorize
                    // Permite acesso público ao cadastro de usuário
                    .requestMatchers("/api/usuarios").permitAll()
                    // Permite acesso público ao login
                    .requestMatchers("/api/usuarios/login").permitAll()
                    // Exige autenticação para todas as outras requisições
                    .anyRequest().authenticated()
                );
        return http.build();
    }
}
