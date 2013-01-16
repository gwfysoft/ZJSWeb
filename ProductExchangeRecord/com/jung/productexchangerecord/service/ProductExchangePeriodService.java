package com.jung.productexchangerecord.service;

import com.jung.common.JqueryGridService;
import com.jung.productexchangerecord.model.ProductExchangePeriod;

public interface ProductExchangePeriodService extends JqueryGridService {
	public boolean addProductExchangePeriod(ProductExchangePeriod productExchangePeriod);
	public boolean deleteProductExchangePeriod(ProductExchangePeriod productExchangePeriod);
	public boolean deleteProductExchangePeriodById(int productExchangePeriodID);
	public ProductExchangePeriod getProductExchangePeriodById(int productExchangePeriodID);
	public boolean updateProductExchangePeriod(ProductExchangePeriod productExchangePeriod);
}
