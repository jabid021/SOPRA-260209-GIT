package fr.bibliotek.security.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.bibliotek.model.Utilisateur;
import fr.bibliotek.repo.UtilisateurRepository;


@Service
public class JpaUserDetailsService implements UserDetailsService {
    private final UtilisateurRepository repository;

    public JpaUserDetailsService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Utilisateur utilisateur = this.repository
            .findByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("Utilisateur non trouvé"));

        return User.withUsername(username)
            .password(utilisateur.getPassword())
            .roles("USER")
            .build();
    }
}
