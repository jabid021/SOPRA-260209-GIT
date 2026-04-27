package fr.formation.security.api;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.formation.request.AuthRequest;
import fr.formation.response.AuthResponse;
import fr.formation.security.service.SecurityService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthApiController {
    private final SecurityService service;

    public AuthApiController(SecurityService service) {
        this.service = service;
    }

    @PostMapping
    public AuthResponse auth(@Valid @RequestBody AuthRequest request) {
        return this.service.auth(request);
    }
}
