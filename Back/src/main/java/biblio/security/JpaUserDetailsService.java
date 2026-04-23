// MARTIN

package biblio.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import biblio.dao.IDAOUtilisateur;

@Service
public class JpaUserDetailsService implements UserDetailsService 
{
    @Autowired
    private IDAOUtilisateur daoUtilisateur;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.daoUtilisateur
                .findByUsername(username)
                .map(u -> User.builder()
                        .username(u.getUsername())
                        .password(u.getPassword())
                        .build()
                )
                .orElseThrow(() -> new UsernameNotFoundException("Utilisateur introuvable : " + username));
    }
}
