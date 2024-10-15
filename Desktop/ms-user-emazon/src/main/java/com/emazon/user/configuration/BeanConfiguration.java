package com.emazon.user.configuration;

import com.emazon.user.adapters.driven.jpa.mysql.adapter.UserJpaAdapter;
import com.emazon.user.adapters.driven.jpa.mysql.mapper.UserEntityMapper;
import com.emazon.user.adapters.driven.jpa.mysql.repository.RoleRepository;
import com.emazon.user.adapters.driven.jpa.mysql.repository.UserRepository;
import com.emazon.user.domain.api.UserServicePort;
import com.emazon.user.domain.api.usecase.UserUseCase;
import com.emazon.user.domain.exceptions.UserWithEmailNotFoundException;
import com.emazon.user.domain.spi.UserPersistencePort;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final UserRepository userRepository;
    private final UserEntityMapper userEntityMapper;
    private final RoleRepository roleRepository;

    @Bean
    UserPersistencePort userPersistencePort() {
        return new UserJpaAdapter(userRepository, userEntityMapper, roleRepository);
    }

    @Bean
    UserServicePort userServicePort() {
        return new UserUseCase(userPersistencePort());
    }

    // Service

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    UserDetailsService userDetailsService() {
        return email -> userRepository.findByEmail(email)
                .orElseThrow(() -> new UserWithEmailNotFoundException(email));
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

}
