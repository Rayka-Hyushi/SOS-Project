package rayka.sos.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
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
//        http
//                // Desabilita o CSRF
//                .csrf(csrf -> csrf.disable())
//                // Define as regras de autorização
//                .authorizeHttpRequests(authorize -> authorize
//                                .requestMatchers("/**").permitAll() // Tudo liberado para testes
//                    // Permite acesso público ao cadastro de usuário
//                    .requestMatchers("/api/usuarios").permitAll()
//                    // Permite acesso público ao login
//                    .requestMatchers("/api/usuarios/login").permitAll()
//                    // Permite acesso público aos endpoints para usuários logados
//                    .requestMatchers("/api/usuarios/**").authenticated()
//                    // Exige autenticação para todas as outras requisições
//                    .anyRequest().authenticated()
//                ).httpBasic(Customizer.withDefaults());
//        return http.build();
        return http
                .csrf((csrf) -> csrf.disable())
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
