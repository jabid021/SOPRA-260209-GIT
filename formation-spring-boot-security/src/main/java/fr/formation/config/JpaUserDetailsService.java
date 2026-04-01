package fr.formation.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.formation.dao.IDAOUtilisateur;
import fr.formation.model.Utilisateur;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IDAOUtilisateur daoUtilisateur;

    // @Override
    // public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    //     Utilisateur utilisateur = this.daoUtilisateur.findByUsername(username);

    //     if (utilisateur == null) {
    //         throw new UsernameNotFoundException("L'utilisateur n'existe pas!");
    //     }

    //     UserDetails ud = User.builder()
    //         .username(username)
    //         // .password("$2a$10$wZppzN.nawtOTtOFaunubeAYHNCWvOrwsNfKStb8n/5L3bTwpxWUW")
    //         .password(utilisateur.getPassword())
    //         .roles(utilisateur.isAdmin() ? "ADMIN" : "USER")
    //         .build()
    //     ;

    //     return ud;
    // }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Utilisateur utilisateur = this.daoUtilisateur
        //     .findByUsernameOptional(username)
        //     .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas!"))
        // ;

        return this.daoUtilisateur
            .findByUsernameOptional(username)
            .map(u -> User.builder()
                .username(username)
                .password(u.getPassword())
                .roles(u.isAdmin() ? "ADMIN" : "USER")
                .build()
            )
            .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas!"))
        ;
    }
}
