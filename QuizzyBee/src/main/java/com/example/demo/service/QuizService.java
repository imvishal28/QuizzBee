package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionsDao;
import com.example.demo.dao.QuizDao;
import com.example.demo.entity.QuestionWrapper;
import com.example.demo.entity.Questions;
import com.example.demo.entity.Quiz;
import com.example.demo.entity.Response;

@Service
public class QuizService {
     @Autowired
	QuizDao quizDao;
     
     @Autowired
     QuestionsDao  questionsDao;

	public ResponseEntity<String> createQuiz(String category, int numQ, String title) {
		 
		List<Questions> questions=questionsDao.findRandomQuestionsByCategory(category,numQ);
		 Quiz quiz=new Quiz();
		 quiz.setTitle(title);
		 quiz.setQuestions(questions);
		 quizDao.save(quiz);
		return new ResponseEntity<>("sucess",HttpStatus.OK) ;
	}

	public ResponseEntity<List<QuestionWrapper>> getQuizQuestion(Integer id) {
		Optional<Quiz> quiz= quizDao.findById(id);
		List<Questions> questionsFromDb=quiz.get().getQuestions();
		List<QuestionWrapper> questionsForUser=new ArrayList<>();
		for(Questions q: questionsFromDb)
		{
			
			QuestionWrapper qw=new QuestionWrapper(q.getId(), q.getQuestion_text(), q.getOption1(),q.getOption2() , q.getOption3(), q.getOption4());
		       questionsForUser.add(qw);
		}
		
		
		return new ResponseEntity<>(questionsForUser,HttpStatus.OK) ;
	}

	public ResponseEntity<Integer> calculateResult(Integer id, List<Response> responses) {
		Quiz quiz=quizDao.findById(id).get();
		List<Questions> questions=quiz.getQuestions();
		int correct=0;
		int i=0;
		for(Response response : responses)
		{
			if(response.getResponse().equals(questions.get(i).getCorrect_option())){
				correct++;
				i++;
			}		
			
		}
		return new ResponseEntity<>(correct,HttpStatus.OK);
	}
}
	

