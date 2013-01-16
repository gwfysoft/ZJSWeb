package com.jung.productexchangerecord.dao;

import com.jung.productexchangerecord.model.ProductExchangePeriod;

public interface ProductExchangePeriodHiberanteDao {

	public boolean addProductExchangePeriod(ProductExchangePeriod productExchangePeriod);

	public boolean deleteProductExchangePeriod(ProductExchangePeriod productExchangePeriod);

	public boolean deleteProductExchangePeriodById(int productExchangePeriodID);

	public ProductExchangePeriod getProductExchangePeriodById(int productExchangePeriodID);

	public boolean updateProductExchangePeriod(ProductExchangePeriod productExchangePeriod);

}
