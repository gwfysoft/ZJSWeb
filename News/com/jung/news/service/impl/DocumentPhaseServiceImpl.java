package com.jung.news.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hp.util.Page;
import com.hp.util.PageContext;
import com.hp.xquery.Restrictions;
import com.hp.xquery.SimpleQuery;
import com.jung.news.dao.DocumentPhaseDao;
import com.jung.news.dao.DocumentPhaseHibernateDao;
import com.jung.news.model.DocumentPhase;
import com.jung.news.service.DocumentPhaseService;

@Transactional
@Service
public class DocumentPhaseServiceImpl implements DocumentPhaseService {

	private static final long serialVersionUID = 6402769052032220445L;
	private DocumentPhaseHibernateDao documentPhaseHibernateDao;
	private DocumentPhaseDao documentPhaseDao;

	@Resource
	public void setDocumentPhaseHibernateDao(DocumentPhaseHibernateDao documentPhaseHibernateDao) {
		this.documentPhaseHibernateDao = documentPhaseHibernateDao;
	}

	@Resource
	public void setDocumentPhaseDao(DocumentPhaseDao documentPhaseDao) {
		this.documentPhaseDao = documentPhaseDao;
	}

	public PageContext getEntityPage(PageContext pageContext, Map<String, String> queryConditions, String orderProperty, String orderMode) {
		SimpleQuery query = documentPhaseDao.getSimpleQuery();
		if (queryConditions != null) {
			String documentPhaseName = queryConditions.get("documentPhaseName").trim();
			if (StringUtils.hasLength(documentPhaseName)) {
				query.addFilter(Restrictions.like("documentPhaseName", "%" + documentPhaseName + "%"));
			}
		}
		if (orderProperty != null && !"".equals(orderProperty)) {
			if (orderMode.equals("asc")) {
				query.addOrder(orderProperty, true);
			} else if (orderMode.equals("desc")) {
				query.addOrder(orderProperty, false);
			}
		}
		Page page = documentPhaseDao.find(query, pageContext.getPageNumber(), pageContext.getPageSize());
		pageContext.setPage(page);
		return pageContext;
	}

	@Override
	public String deleteDocumentPhasees(String ids) {
		if (ids != null && !ids.equals("")) {
			String[] idArr = ids.split(",");
			if (idArr != null) {
				try {
					for (String id : idArr) {
						deleteDocumentPhaseById(Integer.parseInt(id));
					}
					return "success";
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return "fail";
	}

	public DocumentPhase findDocumentPhaseById(int id) {
		return documentPhaseHibernateDao.getDocumentPhaseById(id);
	}

	public boolean support(String entityName) {
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(DocumentPhase.REF)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateDocumentPhase(DocumentPhase documentPhase) {
		return documentPhaseHibernateDao.updateDocumentPhase(documentPhase);
	}

	@Override
	public boolean addDocumentPhase(DocumentPhase documentPhase) {
		return documentPhaseHibernateDao.addDocumentPhase(documentPhase);
	}

	@Override
	public boolean deleteDocumentPhaseById(int id) {
		return documentPhaseHibernateDao.deleteDocumentPhaseById(id);
	}

	@Override
	public List<DocumentPhase> findDocumentPhaseList() {
		return documentPhaseHibernateDao.findDocumentPhaseList();
	}
}
