package com.quest.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.quest.dao.IDAOPersonne;

@Service
public class JpaUserDetailsService implements UserDetailsService {
    @Autowired
    private IDAOPersonne daoPersonne;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return this.daoPersonne
            .findByLogin(username)
            .map(u -> User.builder()
                .username(username)
                .password(u.getPassword())
                .build()
            )
            .orElseThrow(() -> new UsernameNotFoundException("L'utilisateur n'existe pas!"))
        ;
    }
}
