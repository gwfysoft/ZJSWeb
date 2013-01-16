package com.jung.news.action;

import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jung.common.JqueryGridAction;
import com.jung.news.model.News;
import com.jung.news.service.NewsService;
import com.opensymphony.xwork2.ActionContext;

public class NewsAction extends JqueryGridAction {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(NewsAction.class);

	private String result;

	private String newsID;
	private NewsService newsService;

	private News news;

	/**
	 * 添加/修改公告在这同一个方法里面,ajax
	 */
	public String addNews() {
		News newsTemp;
		if (newsID!=null&&!newsID.equals("")) {
			if(news==null){
				//返回修改界面
				news=newsService.findNewsById(Integer.parseInt(newsID));
				this.result = SUCCESS;
				return SUCCESS;
			}else{
				//修改
				newsTemp = newsService.findNewsById(Integer.parseInt(newsID));
				newsTemp.setNewsTitle(news.getNewsTitle());
				newsTemp.setNewsContent(news.getNewsContent());
				newsTemp.setNewsType(news.getNewsType());
				if (newsService.updateNews(newsTemp)) {
					logger.info("更新公告成功");
					this.result = SUCCESS;
					return SUCCESS;
				}
			}
		} else {
			//新增
			news.setRealeaser("yy");
			news.setPublishTime(new Date());
			String path=(String) ActionContext.getContext().getSession().get("filePath");
			news.setDocumentPath(path);
			if (newsService.addNews(news)) {
				logger.info(" 保存公告成功");
				this.result = SUCCESS;
				return SUCCESS;
			}

		}
		return FAIL;
	}

	/**
	 * 删除公告
	 * 
	 * @return
	 */
	public String deleteNews() {
		if (newsService.deleteNewsById(Integer.parseInt(newsID))) {
			logger.info("删除公告成功");
			this.result = SUCCESS;
		} else {
			this.result = FAIL;
			return FAIL;
		}
		return FAIL;
	}

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Resource
	public void setNewsService(NewsService newsService) {
		this.newsService = newsService;
	}

	/**
	 * @param newsID
	 *            the newsID to set
	 */
	public void setNewsID(String newsID) {
		this.newsID = newsID;
	}

	/**
	 * @return the newsID
	 */
	public String getNewsID() {
		return newsID;
	}

}
