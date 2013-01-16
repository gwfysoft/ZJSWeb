package com.jung.productexchangerecord.service.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.util.StringUtils;

import com.hp.util.Page;
import com.hp.util.PageContext;
import com.hp.xquery.Restrictions;
import com.hp.xquery.SimpleQuery;
import com.jung.productexchangerecord.dao.ProductExchangePeriodDao;
import com.jung.productexchangerecord.dao.ProductExchangePeriodHiberanteDao;
import com.jung.productexchangerecord.model.ProductExchangePeriod;
import com.jung.productexchangerecord.service.ProductExchangePeriodService;

public class ProductExchangePeriodServiceImpl implements ProductExchangePeriodService {

	private static final long serialVersionUID = 1L;
	private ProductExchangePeriodDao productExchangePeriodDao;
	private ProductExchangePeriodHiberanteDao productExchangePeriodHiberanteDao;

	@Override
	public boolean addProductExchangePeriod(ProductExchangePeriod productExchangePeriod) {
		return productExchangePeriodHiberanteDao.addProductExchangePeriod(productExchangePeriod);
	}

	@Override
	public boolean deleteProductExchangePeriod(ProductExchangePeriod productExchangePeriod) {
		return productExchangePeriodHiberanteDao.deleteProductExchangePeriod(productExchangePeriod);
	}

	@Override
	public boolean deleteProductExchangePeriodById(int productExchangePeriodID) {
		return productExchangePeriodHiberanteDao.deleteProductExchangePeriodById(productExchangePeriodID);
	}

	@Override
	public PageContext getEntityPage(PageContext pageContext, Map<String, String> queryConditions, String orderProperty, String orderMode) {
		SimpleQuery query = productExchangePeriodDao.getSimpleQuery();
		if (queryConditions != null) {
			String productExchangePeriodName = queryConditions.get("");
			if (StringUtils.hasLength(productExchangePeriodName)) {
				query.addFilter(Restrictions.like("productExchangePeriodName", "%" + productExchangePeriodName + "%"));
			}
		}
		if (orderProperty != null && !"".equals(orderProperty)) {
			if (orderMode.equals("asc")) {
				query.addOrder(orderProperty, true);
			} else if (orderMode.equals("desc")) {
				query.addOrder(orderProperty, false);
			}
		}
		Page page = productExchangePeriodDao.find(query, pageContext.getPageNumber(), pageContext.getPageSize());
		pageContext.setPage(page);
		return pageContext;
	}

	@Override
	public ProductExchangePeriod getProductExchangePeriodById(int productExchangePeriodID) {
		return productExchangePeriodHiberanteDao.getProductExchangePeriodById(productExchangePeriodID);
	}

	@Resource
	public void setProductExchangePeriodDao(ProductExchangePeriodDao productExchangePeriodDao) {
		this.productExchangePeriodDao = productExchangePeriodDao;
	}

	/**
	 * @param productExchangePeriodHiberanteDao
	 *            the productExchangePeriodHiberanteDao to set
	 */
	@Resource
	public void setProductExchangePeriodHiberanteDao(ProductExchangePeriodHiberanteDao productExchangePeriodHiberanteDao) {
		this.productExchangePeriodHiberanteDao = productExchangePeriodHiberanteDao;
	}

	@Override
	public boolean support(String entityName) {
		if (entityName != null && entityName.trim().length() != 0) {
			if (entityName.equals(ProductExchangePeriod.REF)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean updateProductExchangePeriod(ProductExchangePeriod productExchangePeriod) {
		return productExchangePeriodHiberanteDao.updateProductExchangePeriod(productExchangePeriod);
	}

}
