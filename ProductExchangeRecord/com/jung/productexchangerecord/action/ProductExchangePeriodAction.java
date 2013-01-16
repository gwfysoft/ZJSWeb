package com.jung.productexchangerecord.action;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.jung.common.JqueryGridAction;
import com.jung.productexchangerecord.model.ProductExchangePeriod;
import com.jung.productexchangerecord.service.ProductExchangePeriodService;

public class ProductExchangePeriodAction extends JqueryGridAction {

	private static final Log logger = LogFactory.getLog(ProductExchangePeriodAction.class);
	private static final long serialVersionUID = 1L;

	private String periodName;
	private String periodId;

	private ProductExchangePeriod productExchangePeriod;

	private ProductExchangePeriodService productExchangePeriodService;

	private String result;

	public String getPeriodName() {
		return periodName;
	}

	public void setPeriodName(String periodName) {
		this.periodName = periodName;
	}

	public String addAndUpdateProductExchangePeriod() {
		if (periodId == null || periodId.equals("")) {
			//productExchangePeriod.setProductExchangePeriodName(periodName);
			productExchangePeriod.setInputUser("ufo");
			productExchangePeriod.setLastUpdate(new Date());
			if (productExchangePeriodService.addProductExchangePeriod(productExchangePeriod)) {
				logger.info("ZJS-----:添加积分兑换期数成功");
				this.result = SUCCESS;
				return SUCCESS;
			}
			return FAIL;
		} else {
			ProductExchangePeriod temp = productExchangePeriodService.getProductExchangePeriodById(Integer.parseInt(this.getPeriodId()));
			temp.setProductExchangePeriodName(productExchangePeriod.getProductExchangePeriodName());
			temp.setInputUser("yy");
			temp.setLastUpdate(new Date());
			if (productExchangePeriodService.updateProductExchangePeriod(temp)) {
				logger.info("ZJS-----:修改积分兑换期数成功");
			}
			return SUCCESS;
		}
	}

	public String deleteProductExchangePeriod() {
		if (productExchangePeriodService.deleteProductExchangePeriodById(Integer.parseInt(periodId))) {
			logger.info("删除积分兑换期数成功");
			this.result = SUCCESS;
		}
		return SUCCESS;
	}


	public String getPeriodId() {
		return periodId;
	}

	public ProductExchangePeriod getProductExchangePeriod() {
		return productExchangePeriod;
	}

	public String getResult() {
		return result;
	}

	public void setPeriodId(String periodId) {
		this.periodId = periodId;
	}

	public void setProductExchangePeriod(ProductExchangePeriod productExchangePeriod) {
		this.productExchangePeriod = productExchangePeriod;
	}

	@Resource
	public void setProductExchangePeriodService(ProductExchangePeriodService productExchangePeriodService) {
		this.productExchangePeriodService = productExchangePeriodService;
	}

	public void setResult(String result) {
		this.result = result;
	}

}
