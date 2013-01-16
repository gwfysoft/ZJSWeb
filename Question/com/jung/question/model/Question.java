package com.jung.question.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.apache.struts2.json.annotations.JSON;

import com.jung.common.ConvertUtil;


@Entity
@Table(name = "Question")
public class Question implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String REF = "Question";
	
	private int questionID;	//自动编号,主键
	//documentCategoryID	//关联文献类别ID
	private Integer questionType;	//题目类型
	private String questionContent;	//问题内容
	private String questionAnswer;	//答案,如果是问答题,则为逗号分隔的关键字
	private Integer questionTotalPoints;	//题目总分
	private Integer questionAnswerPoints;	//关键字得分,每匹配一个关键字累积的分数值
	private Integer questionStatus;	//题目审核状态,0 未审核 1已审核 
	private Date lastDate;	//操作日期
	private String inputUser;	//录入人员
	
	private String questionTypeMapping;//题目类型映射字段
	private String  questionStatusMapping;//题目状态映射字段
	 
	/**
	 * @return the questionID
	 */
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	public int getQuestionID() {
		return questionID;
	}
	/**
	 * @param questionID the questionID to set
	 */
	public void setQuestionID(int questionID) {
		this.questionID = questionID;
	}
	/**
	 * @return the questionType
	 */
	@Column(name="questionType",length=11)
	public Integer getQuestionType() {
		return questionType;
	}
	/**
	 * @param questionType the questionType to set
	 */
	public void setQuestionType(Integer questionType) {
		this.questionType = questionType;
	}
	/**
	 * @return the questionContent
	 */
	@Lob
	@Column(length = 16777216) 
	public String getQuestionContent() {
		return questionContent;
	}
	/**
	 * @param questionContent the questionContent to set
	 */
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	/**
	 * @return the questionAnswer
	 */
	@Column(name = "questionAnswer", length = 255)
	public String getQuestionAnswer() {
		return questionAnswer;
	}
	/**
	 * @param questionAnswer the questionAnswer to set
	 */
	public void setQuestionAnswer(String questionAnswer) {
		this.questionAnswer = questionAnswer;
	}
	/**
	 * @return the questionTotalPoints
	 */
	@Column(name="questionTotalPoints",length=11)
	public Integer getQuestionTotalPoints() {
		return questionTotalPoints;
	}
	/**
	 * @param questionTotalPoints the questionTotalPoints to set
	 */
	public void setQuestionTotalPoints(Integer questionTotalPoints) {
		this.questionTotalPoints = questionTotalPoints;
	}
	/**
	 * @return the questionAnswerPoints
	 */
	@Column(name="questionAnswerPoints",length=11)
	public Integer getQuestionAnswerPoints() {
		return questionAnswerPoints;
	}
	/**
	 * @param questionAnswerPoints the questionAnswerPoints to set
	 */
	public void setQuestionAnswerPoints(Integer questionAnswerPoints) {
		this.questionAnswerPoints = questionAnswerPoints;
	}
	/**
	 * @return the questionStatus
	 */
	@Column(name="questionStatus",length=11)
	public Integer getQuestionStatus() {
		return questionStatus;
	}
	/**
	 * @param questionStatus the questionStatus to set
	 */
	public void setQuestionStatus(Integer questionStatus) {
		this.questionStatus = questionStatus;
	}
	/**
	 * @return the lastDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastDate() {
		if(lastDate!=null){
			lastDate=ConvertUtil.timestampToDate(lastDate);
		}
		return lastDate;
	}
	/**
	 * @param lastDate the lastDate to set
	 */
	public void setLastDate(Date lastDate) {
		this.lastDate = lastDate;
	}
	/**
	 * @return the inputUser
	 */
	@Column(name="inputUser",length=255)
	public String getInputUser() {
		return inputUser;
	}
	/**
	 * @param inputUser the inputUser to set
	 */
	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}
	/**
	 * @param questionStatusMapping the questionStatusMapping to set
	 */
	public void setQuestionStatusMapping(String questionStatusMapping) {
		this.questionStatusMapping = questionStatusMapping;
	}
	/**
	 * @return the questionStatusMapping
	 */
	@JSON(serialize=false)
	@Transient
	public String getQuestionStatusMapping() {
		switch (questionStatus){
		case 0 : return "未审核";
		case 1 : return "已审核";
		default : return "";
		}
	}
	/**
	 * @param questionTypeMapping the questionTypeMapping to set
	 */
	public void setQuestionTypeMapping(String questionTypeMapping) {
		this.questionTypeMapping = questionTypeMapping;
	}
	/**
	 * @return the questionTypeMapping
	 */
	@JSON(serialize=false)
	@Transient
	public String getQuestionTypeMapping() {
		switch (questionType){
		case 0 : return "选择题";
		case 1 : return "问答题";
		default : return "";
		}
	}
}
