package com.jung.news.service;

import java.util.List;

import com.jung.common.JqueryGridService;
import com.jung.news.model.DocumentPhase;

public interface DocumentPhaseService extends JqueryGridService {

	public boolean addDocumentPhase(DocumentPhase documentPhase);

	public boolean updateDocumentPhase(DocumentPhase documentPhase);

	public boolean deleteDocumentPhaseById(int id);

	public DocumentPhase findDocumentPhaseById(int id);

	public List<DocumentPhase> findDocumentPhaseList();

	String deleteDocumentPhasees(String ids);
}
