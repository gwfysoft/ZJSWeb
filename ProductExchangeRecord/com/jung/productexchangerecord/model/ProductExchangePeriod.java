package com.jung.productexchangerecord.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
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
 * 产品兑换期数表
 */
@Entity
@Table(name = "ProductExchangePeriod")
public class ProductExchangePeriod implements Serializable {

	private static final long serialVersionUID = 1L;
	public static String REF = "ProductExchangePeriod";
	@Access(AccessType.PROPERTY)
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "periodId", unique = true, nullable = false)
	private Integer periodId;// 主键
	private String productExchangePeriodName;// 期数名称
	private String inputUser;// 录入人
	private Date lastUpdate;// 录入时间


	public Integer getPeriodId() {
		return periodId;
	}

	public void setPeriodId(Integer periodId) {
		this.periodId = periodId;
	}

	/**
	 * @return the productExchangePeriodName
	 */
	@Column(name = "productExchangePeriodName", length = 255)
	public String getProductExchangePeriodName() {
		return productExchangePeriodName;
	}

	/**
	 * @param productExchangePeriodName
	 */
	public void setProductExchangePeriodName(String productExchangePeriodName) {
		this.productExchangePeriodName = productExchangePeriodName;
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
