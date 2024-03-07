package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Questions;
import com.example.demo.service.QuestionService;

@RestController
@RequestMapping("questions")
public class QuestionsController {
	@Autowired
	QuestionService questionService;
	
	//getting all the question in databases
     @GetMapping("getAllQuestions")
	public ResponseEntity<List<Questions>> getAllQuestions()
	{
		return questionService.getAllQuestions();
	}
     
     @GetMapping("category/{category}")
     public ResponseEntity<List<Questions>> getQuestionsByCategory(@PathVariable String category)
     {
    	 //it will return questions based on the category  in List type
    	 return questionService.getQuestionsByCategory(category);
     }
     
     @PostMapping("add")
     public ResponseEntity<String> addQuestions(@RequestBody Questions question)
     {
    	 //it will add the question 
    	 questionService.addQuestions(question);
    	  return new ResponseEntity<>( "Uploded a Question", HttpStatus.CREATED);
     }
     @PostMapping("delete/{id}")
     public ResponseEntity<String> deleteQuestions(@PathVariable int id)
     {
    	  questionService.deleteQuestions(id);
    	 return new ResponseEntity<>( "Deleted a Question", HttpStatus.OK);
     }

}
