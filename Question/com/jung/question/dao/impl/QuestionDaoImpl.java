package com.jung.question.dao.impl;

import java.util.List;

import com.jung.question.dao.QuestionDao;
import com.jung.question.model.Question;
import com.hp.util.Page;
import com.hp.util.dao.hibernate.HibernateBaseDao;
import com.hp.xquery.SimpleQuery;

public class QuestionDaoImpl  extends HibernateBaseDao implements QuestionDao {

	@Override
	public Class getEntityType() {
		// TODO Auto-generated method stub
		return Question.class;
	}

}
