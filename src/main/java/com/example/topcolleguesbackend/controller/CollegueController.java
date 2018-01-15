package com.example.topcolleguesbackend.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.topcolleguesbackend.entite.Collegue;
import com.example.topcolleguesbackend.entite.Opinion;
import com.example.topcolleguesbackend.entite.Vote;
import com.example.topcolleguesbackend.repository.CollegueRepository;
import com.example.topcolleguesbackend.repository.VoteRepository;

@RestController
@RequestMapping("/collegues")
@CrossOrigin // Pour donner l'accès au serveur via un navigateur web !
public class CollegueController {

	@Autowired private CollegueRepository collegueRepo;
	@Autowired private VoteRepository voteRepo;
	
	@GetMapping()
	public List<Collegue> listerCollegues() {
		
		return this.collegueRepo.findAll();
	}
	
	@GetMapping(value = "/{pseudo}")
	public Collegue getCollegue(@PathVariable("pseudo") String pseudo) {
		
		return this.collegueRepo.findByPseudo(pseudo);
	}
	
	@PostMapping()
	public List<Collegue> postCollegue(@RequestBody Collegue collegue) {
		
		boolean pseudoDejaExistant = false;
		
		List<Collegue> collegues = this.collegueRepo.findAll();
		for(Collegue c: collegues) {
			if(c.getPseudo().equals(collegue.getPseudo())) {
				pseudoDejaExistant = true;
			}
		}
		
		if(!pseudoDejaExistant) {
			Collegue nouveauCollegue = new Collegue();
			nouveauCollegue.setScore(collegue.getScore());
			nouveauCollegue.setPseudo(collegue.getPseudo());
			nouveauCollegue.setUrl(collegue.getUrl());
			this.collegueRepo.save(nouveauCollegue);
		}
		
		return this.collegueRepo.findAll();
	}
	
	@PatchMapping(value = "/{pseudo}")
	public Collegue patchCollegue(@PathVariable("pseudo") String pseudo,
			@RequestBody Opinion opinion) {
		Collegue collegue = this.collegueRepo.findByPseudo(pseudo);
		if(collegue != null) {
			if(opinion.getAction().equals("aimer")) {
				collegue.setScore(collegue.getScore()+10);
				Vote v = new Vote("aimé",collegue);
				this.voteRepo.save(v);
				this.collegueRepo.save(collegue);
			}
			else if(opinion.getAction().equals("detester")) {
				collegue.setScore(collegue.getScore()-5);
				Vote v = new Vote("detesté",collegue);
				this.voteRepo.save(v);
				this.collegueRepo.save(collegue);
			}
		}
		return collegue;

	}
	
}
