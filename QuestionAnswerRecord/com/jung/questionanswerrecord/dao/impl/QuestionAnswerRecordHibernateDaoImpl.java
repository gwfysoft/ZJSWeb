package com.jung.questionanswerrecord.dao.impl;

import com.jung.common.HibernateEntityManagerImpl;
import com.jung.exception.SkeletonException;
import com.jung.exception.SkeletonSystemException;
import com.jung.questionanswerrecord.dao.QuestionAnswerRecordHibernateDao;
import com.jung.questionanswerrecord.model.QuestionAnswerRecord;

public class QuestionAnswerRecordHibernateDaoImpl extends HibernateEntityManagerImpl<QuestionAnswerRecord> implements
		QuestionAnswerRecordHibernateDao {

	@Override
	public Class<QuestionAnswerRecord> getEntityType() {
		// TODO Auto-generated method stub
		return QuestionAnswerRecord.class;
	}

	@Override
	public boolean addQuestionAnswerRecord(
			QuestionAnswerRecord questionAnswerRecord) {
		// TODO Auto-generated method stub
		try {
			super.save(questionAnswerRecord);
			return true;
		} catch (SkeletonSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteQuestionAnswerRecordByID(int questionAnswerRecordID) {
		// TODO Auto-generated method stub
		String sql="delete from questionanswerrecord where questionAnswerID="+questionAnswerRecordID;
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
	public boolean updateQuestionAnswerRecord(
			QuestionAnswerRecord questionAnswerRecord) {
		// TODO Auto-generated method stub
		try {
			super.update(questionAnswerRecord);
			return true;
		} catch (SkeletonSystemException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}



}
