package io.student.rococo.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.student.rococo.service.cors.CorsCustomizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

@EnableWebSecurity
@EnableMethodSecurity
@Configuration
public class RococoApiConfiguration {
    private final CorsCustomizer corsCustomizer;

    @Autowired
    public RococoApiConfiguration(CorsCustomizer corsCustomizer) {
        this.corsCustomizer = corsCustomizer;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        corsCustomizer.corsCustomizer(http);
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(customizer ->
                        customizer
                                .requestMatchers(HttpMethod.GET, "/api/session").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/artist/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/painting/**").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/museum/**").permitAll()
                                .anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
        return http.build();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ssZ");
        objectMapper.setDateFormat(dateFormat);
        return objectMapper;
    }
}
