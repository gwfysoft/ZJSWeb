package com.jung.questionanswerrecord.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.common.JqueryGridAction;
import com.jung.question.action.QuestionAction;
import com.jung.questionanswerrecord.model.QuestionAnswerRecord;
import com.jung.questionanswerrecord.service.QuestionAnswerRecordService;

public class QuestionAnswerRecordAction extends JqueryGridAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(QuestionAnswerRecordAction.class);

	
	private QuestionAnswerRecord questionAnswerRecord;
	private String questionAnswerRecordID;
	private String result;


	private QuestionAnswerRecordService questionAnswerRecordService;

	/**
	 * @param questionAnswerRecordService the questionAnswerRecordService to set
	 */
	public String addQuestionAnswerRecordO(){
		return null;
		
	}
	public String deleteQuestionAnswerRecord(){
		if(questionAnswerRecordService.deleteQuestionAnswerRecordByID(Integer.parseInt(questionAnswerRecordID))){
			logger.info("删除答题记录成功");
			this.result=SUCCESS;
			return SUCCESS;
		}else{
			return FAIL;
		}
	}
	
	@Resource
	public void setQuestionAnswerRecordService(
			QuestionAnswerRecordService questionAnswerRecordService) {
		this.questionAnswerRecordService = questionAnswerRecordService;
	}

	/**
	 * @param questionAnswerRecord the questionAnswerRecord to set
	 */
	public void setQuestionAnswerRecord(QuestionAnswerRecord questionAnswerRecord) {
		this.questionAnswerRecord = questionAnswerRecord;
	}
	/**
	 * @return the questionAnswerRecordID
	 */
	public String getQuestionAnswerRecordID() {
		return questionAnswerRecordID;
	}
	/**
	 * @param questionAnswerRecordID the questionAnswerRecordID to set
	 */
	public void setQuestionAnswerRecordID(String questionAnswerRecordID) {
		this.questionAnswerRecordID = questionAnswerRecordID;
	}

	/**
	 * @return the questionAnswerRecord
	 */
	public QuestionAnswerRecord getQuestionAnswerRecord() {
		return questionAnswerRecord;
	}
	/**
	 * @param result the result to set
	 */
	public void setResult(String result) {
		this.result = result;
	}
	/**
	 * @return the result
	 */
	public String getResult() {
		return result;
	}

}
