package com.quest.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.model.Personne;


public interface IDAOPersonne extends JpaRepository<Personne, Integer> {
    public Optional<Personne> findByLogin(String login);
}
