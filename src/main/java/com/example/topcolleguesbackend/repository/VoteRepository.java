package com.example.topcolleguesbackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.topcolleguesbackend.entite.Vote;

public interface VoteRepository extends JpaRepository<Vote,Integer> {
	
	List<Vote> findFirst3ByOrderByIdDesc();
	
	List<Vote> findByIdGreaterThan(Integer i);

}
