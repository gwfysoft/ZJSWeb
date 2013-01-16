package com.jung.questionanswerrecord.dao;

import com.jung.questionanswerrecord.model.QuestionAnswerRecord;

public interface QuestionAnswerRecordHibernateDao {

	public boolean addQuestionAnswerRecord(QuestionAnswerRecord questionAnswerRecord);
	public boolean updateQuestionAnswerRecord(QuestionAnswerRecord questionAnswerRecord);
	public boolean deleteQuestionAnswerRecordByID(int questionAnswerRecordID);
}
