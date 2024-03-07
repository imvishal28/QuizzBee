package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Questions;


public interface QuestionsDao extends JpaRepository<Questions, Integer> {
	List<Questions> findByCategory(String category);
    
	
	@Query(value="select * from Questions q where q.category=:category order by RAND() limit :numQ ",nativeQuery=true)
	List<Questions> findRandomQuestionsByCategory(String category, int numQ);
}
