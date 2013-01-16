package com.jung.news.dao.impl;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import com.jung.common.HibernateEntityManagerImpl;
import com.jung.exception.SkeletonException;
import com.jung.exception.SkeletonSystemException;
import com.jung.news.dao.DocumentPhaseHibernateDao;
import com.jung.news.model.DocumentPhase;

@Repository
public class DocumentPhaseHibernateDaoImpl extends
		HibernateEntityManagerImpl<DocumentPhase> implements
		DocumentPhaseHibernateDao {
	private static final Log logger = LogFactory
			.getLog(DocumentPhaseHibernateDaoImpl.class);

	@Override
	public boolean addDocumentPhase(DocumentPhase documentPhase) {
		try {
			super.saveOrUpdate(documentPhase);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDocumentPhase(DocumentPhase documentPhase) {
		try {
			super.remove(documentPhase);
			return true;
		} catch (SkeletonSystemException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteDocumentPhaseById(int documentPhaseId) {
		String sql = "delete from DocumentPhase where documentPhaseID="
				+ documentPhaseId;
		try {
			super.executeSQLUpdate(sql);
			return true;
		} catch (SkeletonException e) {
			logger.warn("ZJS --- Dao exception:" + this.getClass().getName()
					+ sql);
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public DocumentPhase getDocumentPhaseById(int id) {
		String hql = "from DocumentPhase where documentPhaseID=" + id;
		try {
			List documentPhaseList = super.executeHql(hql);
			if (documentPhaseList != null && documentPhaseList.size() > 0) {
				return (DocumentPhase) documentPhaseList.get(0);
			}
		} catch (SkeletonException e) {
			logger.warn("ZJS --- Dao exception:" + this.getClass().getName()
					+ hql);
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Class<DocumentPhase> getEntityType() {
		return DocumentPhase.class;
	}

	@Override
	public boolean updateDocumentPhase(DocumentPhase documentPhase) {
		try {
			super.update(documentPhase);
			return true;
		} catch (SkeletonSystemException e) {
			logger.warn("ZJS --- Dao exception:" + this.getClass().getName()
					+ ">updateDocumentPhase()");
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<DocumentPhase> findDocumentPhaseList() {
		String hql = " from DocumentPhase  where  1=1 order by lastUpdate desc ";
		try {
			List documentPhaseList = super.executeHql(hql);
			if (documentPhaseList != null && documentPhaseList.size() > 0) {
				return documentPhaseList;
			}
		} catch (SkeletonException e) {
			logger.warn("ZJS --- Dao exception:" + this.getClass().getName()
					+ hql);
			e.printStackTrace();
		}
		return null;
	}
}
