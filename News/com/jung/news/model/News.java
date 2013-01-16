package com.jung.news.model;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.text.ParseException;
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

import com.jung.common.ConvertUtil;

/**
 * 新闻
 *
 */
@Entity
@Table(name = "news")
public class News implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    public static String REF = "News";
    
	private int newsID;//主键
	
	private String newsTitle;//新闻标题,共用
	private String newsSeq;//排序位置,共用
	private String newsContent;//新闻内容,共用
	
	private Integer documentPhaseID;//文献期数ID,文献用
	private String documentType;//所属文献,文献用
	private String documentPath;//文献下载路径,文献用
	
	private Integer newsType;//新闻类别	1网站公告 2活动公告 3文献资料
	private String realeaser;//发布人
	private Date publishTime;//发布时间
	
	private String newsTypeMapping;
	/**
	 * @return the newsID
	 */
	@Id
	@GeneratedValue(strategy =GenerationType.AUTO)
	@Column(name = "newsID", unique = true, nullable = false)
	public int getNewsID() {
		return newsID;
	}
	/**
	 * @param newsID the newsID to set
	 */
	public void setNewsID(int newsID) {
		this.newsID = newsID;
	}
	/**
	 * @return the newsTitle
	 */
	@Column(name="newsTitle",length=255)
	public String getNewsTitle() {
		return newsTitle;
	}
	/**
	 * @param newsTitle the newsTitle to set
	 */
	public void setNewsTitle(String newsTitle) {
		this.newsTitle = newsTitle;
	}
	/**
	 * @return the newsSeq
	 */
	@Column(name="newsSeq",length=255)
	public String getNewsSeq() {
		return newsSeq;
	}
	/**
	 * @param newsSeq the newsSeq to set
	 */
	public void setNewsSeq(String newsSeq) {
		this.newsSeq = newsSeq;
	}
	/**
	 * @return the newsContent
	 */
	@Lob
	@Column(length = 16777216) 
	public String getNewsContent() {
		return newsContent;
	}
	/**
	 * @param newsContent the newsContent to set
	 */
	public void setNewsContent(String newsContent) {
		this.newsContent = newsContent;
	}
	/**
	 * @return the newsType
	 */
	@Column(name="newsType",length=11)
	public int getNewsType() {
		return newsType;
	}
	/**
	 * @param newsType the newsType to set
	 */
	public void setNewsType(int newsType) {
		this.newsType = newsType;
	}
	/**
	 * @return the documentPhaseID
	 */
	@Column(name="documentPhaseID",length=11)
	public Integer getDocumentPhaseID() {
		return documentPhaseID;
	}
	/**
	 * @param documentPhaseID the documentPhaseID to set
	 */
	public void setDocumentPhaseID(Integer documentPhaseID) {
		this.documentPhaseID = documentPhaseID;
	}
	/**
	 * @return the documentType
	 */
	@Column(name="documentType",length=255)
	public String getDocumentType() {
		return documentType;
	}
	/**
	 * @param documentType the documentType to set
	 */
	public void setDocumentType(String documentType) {
		this.documentType = documentType;
	}
	/**
	 * @return the documentPath
	 */
	@Column(name="documentPath",length=255)
	public String getDocumentPath() {
		return documentPath;
	}
	/**
	 * @param documentPath the documentPath to set
	 */
	public void setDocumentPath(String documentPath) {
		this.documentPath = documentPath;
	}
	/**
	 * @return the realeaser
	 */
	@Column(name="realeaser",length=255)
	public String getRealeaser() {
		return realeaser;
	}
	/**
	 * @param realeaser the realeaser to set
	 */
	public void setRealeaser(String realeaser) {
		this.realeaser = realeaser;
	}
	/**
	 * @return the publishTime
	 */
	@Temporal(TemporalType.TIMESTAMP)
	public Date getPublishTime() {
		if(publishTime!=null){
			publishTime=ConvertUtil.timestampToDate(publishTime);
		}
		return publishTime;
	}
	/**
	 * @param publishTime the publishTime to set
	 */
	public void setPublishTime(Date publishTime) {
		this.publishTime = publishTime;
	}
	/**
	 * @param newsTypeMapping the newsTypeMapping to set
	 */
	public void setNewsTypeMapping(String newsTypeMapping) {
		this.newsTypeMapping = newsTypeMapping;
	}
	/**
	 * @return the newsTypeMapping
	 */
	@Transient
	public String getNewsTypeMapping() {
		switch (newsType) {
		case 1:
			return "网站公告";
		case 2:
			return "活动公告";
		case 3:
			return "文献资料";
		default:
			return "";
		}
	}
	
}
