package com.jung.question.dao;

import com.jung.question.model.Question;

public interface QuestionHibernateDao {

	public boolean addQuestion(Question question);
	public boolean updateQuestion(Question question);
	public boolean deleteQuestionByID(int questionID);
	public Question findQuestionByID(int questionID);
}
