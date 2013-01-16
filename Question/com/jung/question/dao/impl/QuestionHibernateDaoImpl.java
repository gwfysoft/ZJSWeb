package com.jung.question.dao.impl;

import java.util.List;

import com.jung.news.model.News;
import com.jung.question.dao.QuestionHibernateDao;
import com.jung.question.model.Question;
import com.jung.common.HibernateEntityManagerImpl;
import com.jung.exception.SkeletonException;
import com.jung.exception.SkeletonSystemException;

public class QuestionHibernateDaoImpl extends HibernateEntityManagerImpl<Question> implements QuestionHibernateDao {

	@Override
	public Class<Question> getEntityType() {
		// TODO Auto-generated method stub
		return Question.class;
	}

	@Override
	public boolean addQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			super.save(question);
			return true;
		} catch (SkeletonSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteQuestionByID(int questionID) {
		// TODO Auto-generated method stub
		String sql="delete from question where question.questionID="+questionID;
		try {
			super.executeSQLUpdate(sql);
			return true;
		} catch (SkeletonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateQuestion(Question question) {
		// TODO Auto-generated method stub
		try {
			super.update(question);
			return true;
		} catch (SkeletonSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	public Question findQuestionByID(int questionID){
		String hql = "from Question where questionID=" + questionID;
		try {
			List questionList = super.executeHql(hql);
			if (questionList != null && questionList.size() > 0) {
				return (Question) questionList.get(0);
			}
		} catch (SkeletonException e) {
			e.printStackTrace();
		}
		return null;
	}

}
