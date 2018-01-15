package com.example.topcolleguesbackend.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.topcolleguesbackend.entite.Vote;
import com.example.topcolleguesbackend.repository.VoteRepository;

import scala.annotation.meta.param;

@RestController
@RequestMapping("/votes")
@CrossOrigin // Pour donner l'accès au serveur via un navigateur web !
public class VoteController {

	@Autowired
	private VoteRepository voteRepo;

	@GetMapping()
	public List<Vote> listerVotes(@RequestParam(value="since",required=false) Integer since) {

		List<Vote> votes = null;

		if (since == null) {
			votes = this.voteRepo.findFirst3ByOrderByIdDesc();
		} else {
			votes = this.voteRepo.findByIdGreaterThan(since);
		}

		return votes;
	}

}
