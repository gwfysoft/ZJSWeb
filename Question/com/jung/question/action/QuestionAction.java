package com.jung.question.action;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.question.model.Question;
import com.jung.question.service.QuestionService;
import com.jung.common.Constants;
import com.jung.common.JqueryGridAction;

public class QuestionAction extends JqueryGridAction {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(QuestionAction.class);

	private String result;
	private String questionID;

	private Question question;
	private QuestionService questionService;

/**
 * 添加、修改题目
 * @return SUCCESS 添加、修改成功 / FAIL 添加、修改失败
 */
	
	public String addQuestion(){
		Question questionTemp;
		if(questionID!=null&&!questionID.equals("")){
			if(question==null){
				//返回修改界面
				question=questionService.findQuestionByID(Integer.parseInt(questionID));
				this.result = SUCCESS;
				return SUCCESS;
			}else{
				//修改
				questionTemp=questionService.findQuestionByID(Integer.parseInt(questionID));
				questionTemp.setQuestionContent(question.getQuestionContent());
				questionTemp.setQuestionAnswer(question.getQuestionAnswer());
				questionTemp.setQuestionStatus(Constants.Question.UNAUDITED);
				questionTemp.setQuestionTotalPoints(question.getQuestionTotalPoints());
				questionTemp.setQuestionType(question.getQuestionType());
				questionTemp.setInputUser("yy");
				if(questionService.updateQuestion(questionTemp)){
					logger.info("更新题目成功");
					this.result=SUCCESS;
					return SUCCESS;
				}else{
					logger.info("更新题目失败");
					return FAIL;
				}
			}
		}else{
			question.setInputUser("yy");
			question.setQuestionStatus(Constants.Question.UNAUDITED);
			if(questionService.addQuestion(question)){
				logger.info("添加题目成功");
				this.result=SUCCESS;
				return SUCCESS;
			}else{
				logger.info("题目失败");
				return FAIL;
			}
		}
	}
	/**
	 * 删除题目
	 * @return SUCCESS 删除成功/  FAIL 删除失败
	 */
	public String deleteQuestion(){
		if(questionService.deleteQuestionByID(Integer.parseInt(questionID))){
			logger.info("删除题目成功");
			this.result=SUCCESS;
			return SUCCESS;
		}else{
		    this.result=FAIL;
		    return FAIL;
		}
	}
	
	@Resource
	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
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
	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
	/**
	 * @return the question
	 */
	public Question getQuestion() {
		return question;
	}
	/**
	 * @return the questionID
	 */
	public String getQuestionID() {
		return questionID;
	}
	/**
	 * @param questionID the questionID to set
	 */
	public void setQuestionID(String questionID) {
		this.questionID = questionID;
	}
	
}
