package com.jung.news.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jung.common.ConvertUtil;

/**
 * 文献期数表
 */
@Entity
@Table(name = "DocumentPhase")
public class DocumentPhase implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String REF = "DocumentPhase";

	private Integer documentPhaseID;// 主键
	private String documentPhaseName;// 期数名称
	private String inputUser;// 录入人
	private Date lastUpdate;// 录入时间

	/**
	 * @return the documentPhaseID
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "documentPhaseID", unique = true, nullable = false)
	public Integer getDocumentPhaseID() {
		return documentPhaseID;
	}

	/**
	 * @param documentPhaseID
	 */
	public void setDocumentPhaseID(Integer documentPhaseID) {
		this.documentPhaseID = documentPhaseID;
	}

	/**
	 * @return the documentPhaseName
	 */
	@Column(name = "documentPhaseName", length = 255)
	public String getDocumentPhaseName() {
		return documentPhaseName;
	}

	/**
	 * @param documentPhaseName
	 */
	public void setDocumentPhaseName(String documentPhaseName) {
		this.documentPhaseName = documentPhaseName;
	}

	/**
	 * @return the inputUser
	 */
	@Column(name = "inputUser", length = 255)
	public String getInputUser() {
		return inputUser;
	}

	/**
	 * @param inputUser
	 */
	public void setInputUser(String inputUser) {
		this.inputUser = inputUser;
	}

	/**
	 * @return the lastUpdate
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getLastUpdate() {
		if (lastUpdate != null) {
			lastUpdate = ConvertUtil.timestampToDate(lastUpdate);
		}
		return lastUpdate;
	}

	/**
	 * @param lastUpdate
	 */
	public void setLastUpdate(Date lastUpdate) {
		this.lastUpdate = lastUpdate;
	}

}
