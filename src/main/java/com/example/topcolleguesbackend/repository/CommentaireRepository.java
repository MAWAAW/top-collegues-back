package com.example.topcolleguesbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.topcolleguesbackend.entite.Commentaire;

public interface CommentaireRepository extends JpaRepository<Commentaire, Integer>{

	public Commentaire findByPseudo(String pseudo);
	public List<Commentaire> findAllByPseudo(String pseudo);
}
