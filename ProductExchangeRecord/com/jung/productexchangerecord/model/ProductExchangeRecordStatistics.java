package com.jung.productexchangerecord.model;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.jung.region.model.Region;
import com.jung.product.model.Product;

@Entity
@Table(name = "ProductExchangeRecordStatistics")
public class ProductExchangeRecordStatistics implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String REF = "ProductExchangeRecordStatistics";
	private Integer id;// 主键
	private Region region;// 地区
	private Product product;// 对应产品
	private Integer totalIntegral;// 总积分
	private Integer totalProduct;// 总产品数
	private Integer totalPeople;// 总人数
	private Date exchangeDate;// 兑换期数

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "regionID")
	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	@ManyToOne(cascade = { CascadeType.REFRESH }, fetch = FetchType.EAGER, optional = true)
	@JoinColumn(name = "productID")
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	@Column(name = "totalIntegral", length = 11)
	public Integer getTotalIntegral() {
		return totalIntegral;
	}

	public void setTotalIntegral(Integer totalIntegral) {
		this.totalIntegral = totalIntegral;
	}

	@Column(name = "totalProduct", length = 11)
	public Integer getTotalProduct() {
		return totalProduct;
	}

	public void setTotalProduct(Integer totalProduct) {
		this.totalProduct = totalProduct;
	}

	@Column(name = "totalPeople", length = 11)
	public Integer getTotalPeople() {
		return totalPeople;
	}

	public void setTotalPeople(Integer totalPeople) {
		this.totalPeople = totalPeople;
	}

	@Column(name = "exchangeDate", length = 11)
	public Date getExchangeDate() {
		return exchangeDate;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public void setExchangeDate(Date exchangeDate) {
		this.exchangeDate = exchangeDate;
	}

}
