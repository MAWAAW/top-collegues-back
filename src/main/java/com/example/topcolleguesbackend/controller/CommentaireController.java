package com.example.topcolleguesbackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.topcolleguesbackend.entite.Collegue;
import com.example.topcolleguesbackend.entite.Commentaire;
import com.example.topcolleguesbackend.repository.CollegueRepository;
import com.example.topcolleguesbackend.repository.CommentaireRepository;

@RestController
@RequestMapping("/commentaires")
@CrossOrigin // Pour donner l'accès au serveur via un navigateur web !
public class CommentaireController {
	
	@Autowired private CollegueRepository collegueRepo;
	@Autowired private CommentaireRepository commRepo;
	
	@GetMapping(value = "/{pseudo}")
	public List<Commentaire> getCommentaire(@PathVariable("pseudo") String pseudo) {

		return this.commRepo.findAllByPseudo(pseudo);
	}
	
	@PostMapping()
	public List<Commentaire> postCommentaire(@RequestBody Commentaire commentaire) {

		boolean pseudoEstBienExistant = false;
		
		if(this.collegueRepo.findByPseudo(commentaire.getPseudo()) != null) {
			pseudoEstBienExistant = true;
		}
		
		if(pseudoEstBienExistant) {
			Commentaire nouveauCommentaire = new Commentaire();
			nouveauCommentaire.setPseudo(commentaire.getPseudo());
			nouveauCommentaire.setCommentaire(commentaire.getCommentaire());
			this.commRepo.save(nouveauCommentaire);
		}
		
		return this.commRepo.findAll();
	}

}
