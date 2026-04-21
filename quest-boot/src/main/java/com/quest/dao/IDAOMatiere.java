package com.quest.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.quest.model.Matiere;

public interface IDAOMatiere extends JpaRepository<Matiere, Integer> {

}
