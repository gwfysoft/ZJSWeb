package com.jung.news.service.impl;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.hp.util.Page;
import com.hp.util.PageContext;
import com.hp.xquery.LikeMatchMode;
import com.hp.xquery.Restrictions;
import com.hp.xquery.SimpleQuery;
import com.jung.common.ConvertUtil;
import com.jung.news.dao.NewsDao;
import com.jung.news.dao.NewsHibernateDao;
import com.jung.news.model.News;
import com.jung.news.service.NewsService;

@SuppressWarnings("serial")
public class NewsServiceImpl implements NewsService {

	private NewsHibernateDao newsHibernateDao;
	private NewsDao newsDao;

	@Resource
	public void setNewsHibernateDao(NewsHibernateDao newsHibernateDao) {
		this.newsHibernateDao = newsHibernateDao;
	}

	@Resource
	public void setNewsDao(NewsDao newsDao) {
		this.newsDao = newsDao;
	}

	public PageContext getEntityPage(PageContext pageContext,
			Map<String, String> queryConditions, String orderProperty,
			String orderMode) {
		// TODO Auto-generated method stub
		SimpleQuery query = newsDao.getSimpleQuery();
		if (queryConditions != null) {
			String newsTitle = queryConditions.get("newsTitle").trim();
			if (StringUtils.hasLength(newsTitle)) {
				query.addFilter(Restrictions.like("newsTitle", "%" + newsTitle
						+ "%"));
			}
			String newsType=queryConditions.get("newsType").trim();
			if (StringUtils.hasLength(newsType)) {
				query.addFilter(Restrictions.like("newsType", "%" + newsType
						+ "%"));
			}
		}
		if (orderProperty != null && !"".equals(orderProperty)) {
			if (orderMode.equals("asc")) {
				query.addOrder(orderProperty, true);
			} else if (orderMode.equals("desc")) {
				query.addOrder(orderProperty, false);
			}
		}
		Page page = newsDao.find(query, pageContext.getPageNumber(),
				pageContext.getPageSize());
		pageContext.setPage(page);
		return pageContext;
	}

	public boolean addNews(News news) {
		return newsHibernateDao.addNews(news);
	}

	public boolean deleteNewsById(int id) {
		return newsHibernateDao.deleteNewsById(id);
	}

	@Override
	public String deleteNewses(String ids) {
		if (ids != null && !ids.equals("")) {
			String[] idArr = ids.split(",");
			if (idArr != null) {
				try {
					for (String id : idArr) {
						deleteNewsById(Integer.parseInt(id));
					}
					return "success";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "fail";
	}

	public News findNewsById(int id) {
		return newsHibernateDao.getNewsById(id);
	}

	@Override
	public List<News> findNewest(int type) {
		return newsHibernateDao.findNewest(type);
	}

	public boolean support(String entityName) {
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(News.REF)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateNews(News news) {
		// TODO Auto-generated method stub
		return newsHibernateDao.updateNews(news);
	}
}
