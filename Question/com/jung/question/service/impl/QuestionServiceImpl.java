package com.jung.question.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.jung.question.dao.QuestionDao;
import com.jung.question.dao.QuestionHibernateDao;
import com.jung.question.model.Question;
import com.jung.question.service.QuestionService;
import com.hp.util.Page;
import com.hp.util.PageContext;
import com.hp.xquery.Restrictions;
import com.hp.xquery.SimpleQuery;

public class QuestionServiceImpl implements QuestionService {

	private QuestionDao questionDao;
	private QuestionHibernateDao questionHibernateDao;
	@Override
	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		// TODO Auto-generated method stub
		SimpleQuery query = questionDao.getSimpleQuery();
		if (queryConditions != null) {
			String questionContent = queryConditions.get("questionContent").trim();
			if (StringUtils.hasLength(questionContent)) {
				query.addFilter(Restrictions.like("questionContent", "%" + questionContent
						+ "%"));
			}
			String questionType=queryConditions.get("questionType").trim();
			if (StringUtils.hasLength(questionType)) {
				query.addFilter(Restrictions.like("questionType", "%" + questionType
						+ "%"));
			}
		}
		if (orderProperty != null && !"".equals(orderProperty)) {
			if (orderMode.equals("asc")) {
				query.addOrder(orderProperty, true);
			} else if (orderMode.equals("desc")) {
				query.addOrder(orderProperty, false);
			}
		}
		Page page = questionDao.find(query, pageContext.getPageNumber(),
				pageContext.getPageSize());
		pageContext.setPage(page);
		return pageContext;
	}

	@Override
	public boolean support(String entityName) {
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(Question.REF)) {
				return true;
			}
		}
		return false;
		
	}

	/**
	 * @param questionHibernateDao the questionHibernateDao to set
	 */
	@Resource
	public void setQuestionHibernateDao(QuestionHibernateDao questionHibernateDao) {
		this.questionHibernateDao = questionHibernateDao;
	}

	/**
	 * @param questionDao the questionDao to set
	 */
	@Resource
	public void setQuestionDao(QuestionDao questionDao) {
		this.questionDao = questionDao;
	}

	@Override
	public boolean addQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionHibernateDao.addQuestion(question);
	}

	@Override
	public boolean deleteQuestionByID(int questionID) {
		// TODO Auto-generated method stub
		return questionHibernateDao.deleteQuestionByID(questionID);
	}

	@Override
	public boolean updateQuestion(Question question) {
		// TODO Auto-generated method stub
		return questionHibernateDao.updateQuestion(question);
	}

	@Override
	public Question findQuestionByID(int questionID) {
		// TODO Auto-generated method stub
		return questionHibernateDao.findQuestionByID(questionID);
	}

}
