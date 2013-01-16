package com.jung.questionanswerrecord.service.impl;


import java.util.Map;

import javax.annotation.Resource;

import com.hp.util.PageContext;
import com.jung.question.model.Question;
import com.jung.questionanswerrecord.dao.QuestionAnswerRecordDao;
import com.jung.questionanswerrecord.dao.QuestionAnswerRecordHibernateDao;
import com.jung.questionanswerrecord.model.QuestionAnswerRecord;
import com.jung.questionanswerrecord.service.QuestionAnswerRecordService;

public class QuestionAnswerRecordServiceImpl implements
		QuestionAnswerRecordService {

	private QuestionAnswerRecordDao questionAnswerRecordDao;
	private QuestionAnswerRecordHibernateDao questionAnswerRecordHibernateDao;
	@Override
	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean support(String entityName) {
		// TODO Auto-generated method stub
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(QuestionAnswerRecord.REF)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param questionAnswerRecordDao the questionAnswerRecordDao to set
	 */
	@Resource
	public void setQuestionAnswerRecordDao(QuestionAnswerRecordDao questionAnswerRecordDao) {
		this.questionAnswerRecordDao = questionAnswerRecordDao;
	}

	/**
	 * @param questionAnswerRecordHibernateDao the questionAnswerRecordHibernateDao to set
	 */
	@Resource
	public void setQuestionAnswerRecordHibernateDao(
			QuestionAnswerRecordHibernateDao questionAnswerRecordHibernateDao) {
		this.questionAnswerRecordHibernateDao = questionAnswerRecordHibernateDao;
	}

	@Override
	public boolean addQuestionAnswerRecord(
			QuestionAnswerRecord questionAnswerRecord) {
		// TODO Auto-generated method stub
		return questionAnswerRecordHibernateDao.addQuestionAnswerRecord(questionAnswerRecord);
	}

	@Override
	public boolean deleteQuestionAnswerRecordByID(int questionAnswerRecordID) {
		// TODO Auto-generated method stub
		return questionAnswerRecordHibernateDao.deleteQuestionAnswerRecordByID(questionAnswerRecordID);
	}

	@Override
	public boolean updateQuestionAnswerRecord(
			QuestionAnswerRecord questionAnswerRecord) {
		// TODO Auto-generated method stub
		return questionAnswerRecordHibernateDao.updateQuestionAnswerRecord(questionAnswerRecord);
	}

}
