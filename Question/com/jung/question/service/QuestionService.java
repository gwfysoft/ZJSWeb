package com.jung.question.service;

import com.jung.common.JqueryGridService;
import com.jung.question.model.Question;

public interface QuestionService extends JqueryGridService {
	public boolean addQuestion(Question question);
	public boolean updateQuestion(Question question);
	public boolean deleteQuestionByID(int questionID); 
	public Question findQuestionByID(int questionID);
}
