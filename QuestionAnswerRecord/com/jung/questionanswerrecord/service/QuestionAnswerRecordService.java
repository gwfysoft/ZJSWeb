package com.jung.questionanswerrecord.service;

import com.jung.common.JqueryGridService;
import com.jung.questionanswerrecord.model.QuestionAnswerRecord;

public interface QuestionAnswerRecordService extends JqueryGridService {
	public boolean addQuestionAnswerRecord(QuestionAnswerRecord questionAnswerRecord);
	public boolean updateQuestionAnswerRecord(QuestionAnswerRecord questionAnswerRecord);
	public boolean deleteQuestionAnswerRecordByID(int questionAnswerRecordID);
}
