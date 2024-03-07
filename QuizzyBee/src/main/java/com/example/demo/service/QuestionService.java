package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.dao.QuestionsDao;
import com.example.demo.entity.Questions;

@Service
public class QuestionService {
	@Autowired
	QuestionsDao questiondao;

	// ResponseEntity is Extension of HttpEntity that adds
	// an HttpStatusCode status code
	public ResponseEntity<List<Questions>> getAllQuestions() {
		try {
			// fetch the all the questios present inside the databases
			// if all ok the status will ok
			return new ResponseEntity<>(questiondao.findAll(), HttpStatus.OK);

		} catch (Exception e) {
			e.printStackTrace();
			// new instance of the ResponseEntity class.It takes two parameters
			return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

		}
	}

	// Fetch questions from the database based on the specified category using
	// the DAO (Data Access Object) layer.
	public ResponseEntity<List<Questions>> getQuestionsByCategory(String category) {

		// Create a new ResponseEntity object to wrap the list of questions and the HTTP
		// status.
		// The ResponseEntity represents the entire HTTP response: status code, headers,
		// and body.
		// It's parameterized with List<Questions> to specify the type of the response
		// body.
		// HttpStatus.OK indicates that the request has succeeded.
		return new ResponseEntity<>(questiondao.findByCategory(category), HttpStatus.OK);
	}

	public ResponseEntity<String> addQuestions(Questions question) {
		questiondao.save(question);
		return null;

	}

	public ResponseEntity<String> deleteQuestions(int id) {
		questiondao.deleteById(id);
		//delete by the id question
		return null;
	}

}
