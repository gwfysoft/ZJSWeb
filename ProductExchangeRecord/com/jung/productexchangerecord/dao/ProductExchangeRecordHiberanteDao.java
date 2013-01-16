package com.jung.productexchangerecord.dao;

import java.util.Date;
import java.util.List;

import com.jung.productexchangerecord.model.ProductExchangeRecord;
import com.jung.productexchangerecord.model.ProductExchangeRecordStatistics;

public interface ProductExchangeRecordHiberanteDao {

	public boolean addProductExchangeRecord(ProductExchangeRecord productExchangeRecord);

	public boolean deleteProductExchangeRecord(ProductExchangeRecord productExchangeRecord);

	public boolean deleteProductExchangeRecordById(int productExchangeRecordID);

	public ProductExchangeRecord getProductExchangeRecordById(int productExchangeRecordID);
	public boolean deleteProductExchangeRecordByProductID(int productID);

	/**
	 * 
	 * @param productExchangeRecordStatistics
	 * @return
	 */
	public boolean addProductExchangeRecordStatistics(ProductExchangeRecordStatistics productExchangeRecordStatistics);

	public boolean updateProductExchangeRecordStatistics(ProductExchangeRecordStatistics productExchangeRecordStatistics);

	public List<ProductExchangeRecordStatistics> getProductExchangeRecordStatisticsAll();

	public ProductExchangeRecordStatistics getProductExchangeRecordStatisticsById(int productExchangeRecordStatisticsId);

	public ProductExchangeRecordStatistics getProductExchangeRecordStatisticsByRegionIdAndExchangeDate(int productExchangeRecordStatisticsRegionId,Date exchangeDate);

	public List<ProductExchangeRecordStatistics> getProductExchangeRecordStatisticsChildsByRecursion();

	boolean appendOneProductExchangeRecordStatistics(ProductExchangeRecord productExchangeRecord);

}
