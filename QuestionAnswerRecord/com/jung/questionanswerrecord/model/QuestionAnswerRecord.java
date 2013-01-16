package com.jung.questionanswerrecord.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jung.common.ConvertUtil;
import com.jung.doctor.model.Doctor;
import com.jung.question.model.Question;
@Entity
@Table(name = "QuestionAnswerRecord")
public class QuestionAnswerRecord implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public  static final String REF = "QuestionAnswerRecord";
	
	private int questionAnswerID;//	自动编号
	private Question question;	//关联题目ID
	private Doctor doctor;	//关联答题医师ID
	private String doctorQuestionSingleAnswer;	//单选回答答案
	private String doctorQuestionAnswer;	//问答回答答案
	private Integer questionPoints;	//本题目得分
	private Date answerDate;	//答题日期
	
	/**
	 * @return the questionAnswerID
	 */
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	public int getQuestionAnswerID() {
		return questionAnswerID;
	}
	/**
	 * @param questionAnswerID the questionAnswerID to set
	 */
	public void setQuestionAnswerID(int questionAnswerID) {
		this.questionAnswerID = questionAnswerID;
	}
	/**
	 * @return the question
	 */
	@ManyToOne(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinColumn(name="questionID")
	public Question getQuestion() {
		return question;
	}
	/**
	 * @param question the question to set
	 */
	public void setQuestion(Question question) {
		this.question = question;
	}
	/**
	 * @return the doctor
	 */
	@ManyToOne(cascade = { CascadeType.ALL },fetch = FetchType.LAZY)
	@JoinColumn(name="doctorID")
	public Doctor getDoctor() {
		return doctor;
	}
	/**
	 * @param doctor the doctor to set
	 */
	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
	/**
	 * @return the doctorQuestionSingleAnswer
	 */
	@Column(length=255)
	public String getDoctorQuestionSingleAnswer() {
		return doctorQuestionSingleAnswer;
	}
	/**
	 * @param doctorQuestionSingleAnswer the doctorQuestionSingleAnswer to set
	 */
	public void setDoctorQuestionSingleAnswer(String doctorQuestionSingleAnswer) {
		this.doctorQuestionSingleAnswer = doctorQuestionSingleAnswer;
	}
	/**
	 * @return the doctorQuestionAnswer
	 */
	@Lob
	@Column(length = 16777216) 
	public String getDoctorQuestionAnswer() {
		return doctorQuestionAnswer;
	}
	/**
	 * @param doctorQuestionAnswer the doctorQuestionAnswer to set
	 */
	public void setDoctorQuestionAnswer(String doctorQuestionAnswer) {
		this.doctorQuestionAnswer = doctorQuestionAnswer;
	}
	/**
	 * @return the questionPoints
	 */
	@Column(length=11)
	public Integer getQuestionPoints() {
		return questionPoints;
	}
	/**
	 * @param questionPoints the questionPoints to set
	 */
	public void setQuestionPoints(Integer questionPoints) {
		this.questionPoints = questionPoints;
	}
	/**
	 * @param answerDate the answerDate to set
	 */
	public void setAnswerDate(Date answerDate) {
		this.answerDate = answerDate;
	}
	/**
	 * @return the answerDate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getAnswerDate() {
		if(answerDate!=null){
			answerDate=ConvertUtil.timestampToDate(answerDate);
		}
		return answerDate;
	}
	

}
