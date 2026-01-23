package com.sanjeeban.CoreApartmentService.config;


import org.springframework.data.domain.AuditorAware;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AuditorAwareImplementation implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        // Example: return logged-in username
        return Optional.of("system");
    }
}
