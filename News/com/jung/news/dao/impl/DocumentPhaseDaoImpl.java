package com.jung.news.dao.impl;

import com.hp.util.dao.hibernate.HibernateBaseDao;
import com.jung.news.dao.DocumentPhaseDao;
import com.jung.news.model.DocumentPhase;

public class DocumentPhaseDaoImpl extends HibernateBaseDao implements DocumentPhaseDao{

	private static final long serialVersionUID = 2061422806409453913L;

	@Override
	public Class<DocumentPhase> getEntityType() {
		return DocumentPhase.class;
	}

}
