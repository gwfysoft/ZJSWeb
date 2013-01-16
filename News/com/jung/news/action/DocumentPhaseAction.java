package com.jung.news.action;

import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.jung.common.JqueryGridAction;
import com.jung.news.model.DocumentPhase;
import com.jung.news.service.DocumentPhaseService;

public class DocumentPhaseAction extends JqueryGridAction {

	private static final long serialVersionUID = 1L;
	private static final Log logger = LogFactory.getLog(DocumentPhaseAction.class);

	private String result;
	private String documentPhaseID;
	private DocumentPhaseService documentPhaseService;
	private DocumentPhase documentPhase;

	/**
	 * Ajax 添加/修改文献期数
	 */
	public String addAndUpdateDocumentPhase() {
		DocumentPhase documentPhaseTemp;
		if (documentPhaseID != null && !documentPhaseID.equals("")) {
			if (documentPhase == null) {
				documentPhase = documentPhaseService.findDocumentPhaseById(Integer.parseInt(documentPhaseID));
				this.result = SUCCESS;
				return SUCCESS;
			} else {
				documentPhaseTemp = documentPhaseService.findDocumentPhaseById(Integer.parseInt(documentPhaseID));
				documentPhaseTemp.setDocumentPhaseName(documentPhase.getDocumentPhaseName());
				documentPhaseTemp.setInputUser(documentPhase.getInputUser());

				if (documentPhaseService.updateDocumentPhase(documentPhaseTemp)) {
					logger.info("更新期数成功");
					this.result = SUCCESS;
					return SUCCESS;
				}
			}
		} else {
			documentPhase.setInputUser("ufo");
			documentPhase.setLastUpdate(new Date());
			if (documentPhaseService.addDocumentPhase(documentPhase)) {
				logger.info(" 保存期数成功");
				this.result = SUCCESS;
				return SUCCESS;
			}
		}
		return FAIL;
	}

	/**
	 * 删除期数
	 */
	public String deleteDocumentPhase() {
		if (documentPhaseService.deleteDocumentPhaseById(Integer.parseInt(documentPhaseID))) {
			logger.info("删除期数成功");
			this.result = SUCCESS;
		} else {
			this.result = FAIL;
			return FAIL;
		}
		return FAIL;
	}

	public DocumentPhase getDocumentPhase() {
		return documentPhase;
	}

	public void setDocumentPhase(DocumentPhase documentPhase) {
		this.documentPhase = documentPhase;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	@Resource
	public void setDocumentPhaseService(DocumentPhaseService documentPhaseService) {
		this.documentPhaseService = documentPhaseService;
	}

	public void setDocumentPhaseID(String documentPhaseID) {
		this.documentPhaseID = documentPhaseID;
	}

	public String getDocumentPhaseID() {
		return documentPhaseID;
	}

}
