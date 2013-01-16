package com.jung.news.dao;

import java.util.List;

import com.jung.news.model.DocumentPhase;

public interface DocumentPhaseHibernateDao {
	public boolean addDocumentPhase(DocumentPhase news);

	public boolean updateDocumentPhase(DocumentPhase news);

	public boolean deleteDocumentPhase(DocumentPhase news);

	public boolean deleteDocumentPhaseById(int newsId);

	public DocumentPhase getDocumentPhaseById(int id);

	public List<DocumentPhase> findDocumentPhaseList();
}
